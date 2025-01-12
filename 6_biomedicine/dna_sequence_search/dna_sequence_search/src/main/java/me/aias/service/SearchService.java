package me.aias.service;

import io.milvus.client.*;
import me.aias.domain.DNAInfoDto;

import java.util.List;

/**
 * 搜索服务接口
 *
 * @author Calvin
 * @date 2021-12-19
 **/
public interface SearchService {
    // 引擎初始化
    void initSearchEngine() throws ConnectFailedException;

    // 检查是否存在 collection
    HasCollectionResponse hasCollection(MilvusClient client, String collectionName);

    HasCollectionResponse hasCollection(String collectionName) throws ConnectFailedException;

    // 创建 collection
    Response createCollection(
            MilvusClient client, String collectionName, long dimension, long indexFileSize);

    Response createCollection(String collectionName, long dimension) throws ConnectFailedException;

    // 删除 collection
    Response dropCollection(MilvusClient client, String collectionName);

    // 查看 collection 信息
    Response getCollectionStats(MilvusClient client, String collectionName);

    void insertVectors(String collectionName, Long id, List<Float> feature) throws ConnectFailedException;

    void insertVectors(String collectionName, List<Long> vectorIds, List<List<Float>> vectors) throws ConnectFailedException;

    void insertVectors(String collectionName, List<DNAInfoDto> list) throws ConnectFailedException;

    InsertResponse insertVectors(
            MilvusClient client, String collectionName, List<Long> vectorIds, List<List<Float>> vectors);

    // 查询向量数量
    long count(MilvusClient client, String collectionName);

    // 根据ID获取向量
    GetEntityByIDResponse getEntityByID(
            MilvusClient client, String collectionName, List<Long> vectorIds);

    // 搜索向量
    SearchResponse search(String collectionName, long topK, List<List<Float>> vectorsToSearch) throws ConnectFailedException;

    SearchResponse search(
            MilvusClient client,
            String collectionName,
            int nprobe,
            long topK,
            List<List<Float>> vectorsToSearch);

    // 删除向量
    Response deleteVectorsByIds(MilvusClient client, String collectionName, List<Long> vectorIds);

    // 创建 index
    Response createIndex(MilvusClient client, String collectionName);

    Response createIndex(String collectionName) throws ConnectFailedException;

    // 查看索引信息
    GetIndexInfoResponse getIndexInfo(MilvusClient client, String collectionName);

    // 删除 index
    Response dropIndex(MilvusClient client, String collectionName);

    // 压缩 collection
    Response compactCollection(MilvusClient client, String collectionName);

    // 检查 collection 中是否有 partition "tag"
    HasPartitionResponse hasPartition(MilvusClient client, String collectionName, String tag);
}
