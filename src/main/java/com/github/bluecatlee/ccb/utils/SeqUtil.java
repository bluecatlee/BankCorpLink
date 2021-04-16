package com.github.bluecatlee.ccb.utils;

import com.gb.soa.sequence.util.SeqGetUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Lazy(false)
@Scope("singleton")
public class SeqUtil {
   @Value("${dubbo.zookeeper.host.port}")
   private String zkaddress;
   public static final String FI_BL_CASH_DTL_SERIES = "fi_bl_cash_dtl_series";
   public static final String FI_BL_CASH_SHARE_DTL_SERIES = "fi_bl_cash_share_dtl_series";
   public static final String FI_BL_CASH_HDR_SERIES = "fi_bl_cash_hdr_series";
   public static final String SD_BL_TML_STATUS_HIS_LOG_SERIES = "sd_bl_tml_status_his_log_series";
   public static final String SD_BL_SO_TML_PAY_HDR_SERIES = "sd_bl_so_tml_pay_hdr_series";
   public static final String FI_BL_CASH_HDR_CASH_NUM_ID = "fi_bl_cash_hdr_cash_num_id";
   public static final String SYS_CONSUME_SUCCESS_LOG_SERIES = "sys_consume_success_log_series";
   public static final String FI_BL_CASH_DISCOUNT_DTL_SERIES = "fi_bl_cash_discount_dtl_series";

   public SeqUtil() {
      super();
   }

   @PostConstruct
   public void init() {
      SeqGetUtil.initeZkConfig(this.zkaddress);
   }

   public static Long getNoSubSequence(String SeqName) {
      return SeqGetUtil.getNoSubSequence("ccash", SeqName);
   }

   public static Long getNoSubSequence(String subSystem, String SeqName) {
      return SeqGetUtil.getNoSubSequence(subSystem, SeqName);
   }

   public static String getSeqNextValue(String SeqName, String routeId) {
      return SeqGetUtil.getSequence("ccash", SeqName, routeId);
   }

   public static String getSeqNextValue(String subSystem, String SeqName, String routeId) {
      return SeqGetUtil.getSequence(subSystem, SeqName, routeId);
   }

   public static List getNoSubSequenceBath(String SeqName, Integer size) {
      return SeqGetUtil.getNoSubSequenceBath(SeqName, size);
   }

   public static Integer getShardId(Long routerId) {
      return SeqGetUtil.getSharedId(routerId);
   }

   public static Integer getShardId(String routerId) {
      return SeqGetUtil.getSharedId(routerId);
   }
}
