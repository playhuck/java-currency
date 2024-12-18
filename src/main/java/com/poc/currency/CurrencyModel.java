package com.poc.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class CurrencyModel {

    @JsonProperty("result")
    private int result; // 1 : 성공, 2 : DATA코드 오류, 3 : 인증코드 오류, 4 : 일일제한횟수 마감

    @JsonProperty("cur_unit")
    private String currencyCode; // 통화 코드

    @JsonProperty("cur_nm")
    private String currencyCountry; // 국가 코드

    @JsonProperty("ttb")
    private String ttb; // 전신환(송금) 받을 떄

    @JsonProperty("tts")
    private String tts; // 전신환(송금) 보낼 때

    @JsonProperty("deal_bas_r")
    private String dealBasR; // 매매 기준

    @JsonProperty("bkpr")
    private String bkpr; // 장부 가격

    @JsonProperty("yy_efee_r")
    private String yyEfeeR; // 년환가로

    @JsonProperty("ten_dd_efee_r")
    private String tenDdEfeeR; // 10일 환 가로율

    @JsonProperty("kftc_bkpr")
    private String kftcBkpr; // 서울외국환중개 매매기준율

    @JsonProperty("kftc_deal_bas_r")
    private String kftcDealBasR; // 서울외국환중개 장부가격
}
