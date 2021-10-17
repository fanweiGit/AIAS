# 智慧工地检测SDK
支持检测的类别：
- person （人体）
- head   （没戴安全帽）
- helmet （戴安全帽）

### SDK功能
工地安全检测，给出检测框和置信度。
- 提供三个模型：
- 小模型（yolov5s 29.7M）
- 中模型（yolov5m 86.8M）
- 大模型（yolov5l 190.8M）

## 运行小模型例子 - Yolov5sExample
- 测试图片效果（只显示安全帽检测，过滤了其它类别的显示，具体看代码）
![small](https://djl-model.oss-cn-hongkong.aliyuncs.com/AIAS/sec_sdks/images/helmet_head_person_s.jpeg)

## 运行中模型例子 - Yolov5mExample
- 测试图片效果
![medium](https://djl-model.oss-cn-hongkong.aliyuncs.com/AIAS/sec_sdks/images/helmet_head_person_m.jpeg)

## 运行大模型例子 - Yolov5lExample
- 测试图片效果
![large](https://djl-model.oss-cn-hongkong.aliyuncs.com/AIAS/sec_sdks/images/helmet_head_person_l.jpeg)


运行成功后，命令行应该看到下面的信息:
```text
[INFO ] - [
	class: "helmet", probability: 0.89502, bounds: [x=0.956, y=0.525, width=0.044, height=0.067]
	class: "helmet", probability: 0.85951, bounds: [x=0.237, y=0.439, width=0.036, height=0.046]
	class: "helmet", probability: 0.81705, bounds: [x=0.901, y=0.378, width=0.036, height=0.052]
	class: "helmet", probability: 0.80817, bounds: [x=0.250, y=0.399, width=0.029, height=0.040]
	class: "helmet", probability: 0.80528, bounds: [x=0.771, y=0.336, width=0.029, height=0.043]
]
```
