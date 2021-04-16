//package com.github.bluecatlee.ccb.utils;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//@Component
//@Lazy(false)
//@Scope("singleton")
//public class SeqUtil {
//   @Value("${dubbo.zookeeper.host.port}")
//   private String zkaddress;
//
//   public SeqUtil() {
//      super();
//   }
//
//   @PostConstruct
//   public void init() {
//      SeqGetUtil.initeZkConfig(this.zkaddress);
//   }
//
//   public static Long getNoSubSequence(String SeqName) {
//      return SeqGetUtil.getNoSubSequence("ccash", SeqName);
//   }
//
//   public static Long getNoSubSequence(String subSystem, String SeqName) {
//      return SeqGetUtil.getNoSubSequence(subSystem, SeqName);
//   }
//
//   public static String getSeqNextValue(String SeqName, String routeId) {
//      return SeqGetUtil.getSequence("ccash", SeqName, routeId);
//   }
//
//   public static String getSeqNextValue(String subSystem, String SeqName, String routeId) {
//      return SeqGetUtil.getSequence(subSystem, SeqName, routeId);
//   }
//
//   public static List getNoSubSequenceBath(String SeqName, Integer size) {
//      return SeqGetUtil.getNoSubSequenceBath(SeqName, size);
//   }
//
//   public static Integer getShardId(Long routerId) {
//      return SeqGetUtil.getSharedId(routerId);
//   }
//
//   public static Integer getShardId(String routerId) {
//      return SeqGetUtil.getSharedId(routerId);
//   }
//}
