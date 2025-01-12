package me.aias.model;

import ai.djl.Device;
import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;

import java.io.IOException;

/**
 * 文本特征提取模型
 *
 * @author Calvin
 * @date 2021-12-19
 **/
public final class TextEncoderModel {

    private ZooModel<String, float[]> model;
    private Predictor<String, float[]> predictor;

    public void init(String modelUri, boolean isChinese) throws MalformedModelException, ModelNotFoundException, IOException {
        this.model = ModelZoo.loadModel(detectCriteria(modelUri, isChinese));
        this.predictor = model.newPredictor();
    }

    public void close() {
        this.model.close();
        this.predictor.close();
    }

    public float[] predict(String text) throws TranslateException {
        return predictor.predict(text);
    }

    private Criteria<String, float[]> detectCriteria(String modelUri, boolean isChinese) {
        Criteria<String, float[]> criteria =
                Criteria.builder()
                        .setTypes(String.class, float[].class)
                        .optModelUrls(modelUri)
                        // .optModelUrls("/Users/calvin/M-BERT-Base-ViT-B/M-BERT-Base-ViT-B.zip") // Load model from local disk
                        .optTranslator(new TextTranslator(isChinese))
                        .optEngine("PyTorch") // Use PyTorch engine
                        // This model was traced on CPU and can only run on CPU
                        .optDevice(Device.cpu())
                        .optProgress(new ProgressBar())
                        .build();

        return criteria;
    }

}
