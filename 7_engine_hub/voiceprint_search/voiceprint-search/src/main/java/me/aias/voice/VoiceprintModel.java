package me.aias.voice;

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
 * 声纹模型
 *
 * @author Calvin
 * @date 2021-12-12
 **/
public final class VoiceprintModel {

    private ZooModel<float[][], float[]> model;
    private Predictor<float[][], float[]> predictor;

    public void init(String modelUri) throws MalformedModelException, ModelNotFoundException, IOException {
        this.model = ModelZoo.loadModel(detectCriteria(modelUri));
        this.predictor = model.newPredictor();
    }

    public void close() {
        this.model.close();
        this.predictor.close();
    }

    public float[] predict(float[][] mag) throws TranslateException {
        return predictor.predict(mag);
    }

    private Criteria<float[][], float[]> detectCriteria(String modelUri) {
        Criteria<float[][], float[]> criteria =
                Criteria.builder()
                        .setTypes(float[][].class, float[].class)
                        .optModelUrls(modelUri)
                        .optTranslator(new VoiceprintTranslator())
                        .optEngine("PaddlePaddle") // Use PyTorch engine
                        //.optDevice(Device.cpu())
                        .optProgress(new ProgressBar())
                        .build();
        return criteria;
    }

}
