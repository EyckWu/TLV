package com.eyckwu.tlv;

/**
 * @author : EyckWu
 * @date : 2019/10/7
 * desc    :
 */
public interface TLVElement {
    interface Tag{
        /**
         * 基本数据类型
         */
        int PRIMITIVE_FRAME = 0x00;

        /**
         * 私有类型
         */
        int PRIVATE_FRAME = 0x40;

        /**
         * 基本类型数据编码
         */
        int PRIMITIVE_DATA = 0x00;

        /**
         * TLV类型数据编码
         */
        int CONSTRUCTED_DATA = 0x20;
    }
}
