package com.eyckwu.tlv;

import com.eyckwu.utils.Log;

/**
 * @author : EyckWu
 * @date : 2019/10/7
 * desc    : TLV编码实现
 */
public class TLVEncoder {
    private static final String TAG = "TLVEncoder";

    private static byte[] encodeTag(int frameType, int dataType, int tagValue){
        int rawTag = frameType | dataType | tagValue;
        int digit = 0;
        if (tagValue >= 0x1F){
            // mutli byte tag
            rawTag = frameType | dataType | 0x1F;
            if (tagValue < 0x80) {
                rawTag <<= 8;
                rawTag |= tagValue & 0x7F;
            } else if (tagValue < 0x3FFF) {
                rawTag <<= 16;
                rawTag |= (((tagValue & 0x3FFF) >> 7 & 0x7F) | 0x80) << 8;
                rawTag |= ((tagValue & 0x3FFF) & 0x7F);
            } else if (tagValue < 0x3FFFF) {
                rawTag <<= 24;
                rawTag |= (((tagValue & 0x3FFFF) >> 14 & 0x7F) | 0x80) << 16;
                rawTag |= (((tagValue & 0x3FFFF) >> 7 & 0x7F) | 0x80) << 8;
                rawTag |= ((tagValue & 0x3FFFF) & 0x7F);
            }
        }
        return intToByteArray(rawTag, digit);
    }

    public static byte[] encodeTag2(int frameType, int dataType, int tagValue) {
        int result = frameType | dataType | tagValue;
        int digit = 0;
        if (tagValue >= 0x1f) {
            result = frameType | dataType | 0x1f;
            Log.d(TAG, "encodeTag2: result = " + result);
            digit = (int) computeTagDigit(tagValue);
            Log.d(TAG, "encodeTag2: digit = " + digit);
            result <<= 8 * digit;
            Log.d(TAG, "encodeTag2: result = " + result);
            //高位到低位
//			rawTag = encodeTagValueFromHighToLowBit(rawTag, digit, tagValue);
            //低位到高位
            result = encodeValueFromLowToHighBit(result, digit, tagValue);
            Log.d(TAG, "encodeTag2: result = " + result);
        }
        return intToByteArray(result, digit);
    }

    /**
     * 从低位到高位对tagValue进行编码
     *
     * @param result 形参，int类型的只传值进来，因此必须反馈编码结果
     * @param digit
     * @param value
     * @return
     */
    private static int encodeValueFromLowToHighBit(int result, int digit, int value) {
        //低位到高位
        for (int i = 0; i < digit - 1; i++) {
            result |= ((value >> i * 7 & 0x7f) | 0x80) << (digit - 1 - i) * 8;
//			System.out.println("tag:"+Integer.toBinaryString(((tagValue >> i * 7 & 0x7f) | 0x80) << (digit - 1 - i) * 8));
//			System.out.println("rawTag:" + Integer.toBinaryString(rawTag));
        }
//		System.out.println("high:" + Integer.toBinaryString((tagValue >> (digit - 1) * 7) & 0x7f));
        result |= (value >> (digit - 1) * 7) & 0x7f;
        return result;
    }

    private static byte[] intToByteArray(int value, int digit) {
        byte[] result = new byte[digit + 1];
        int length = result.length;
        result[0] = (byte) ((value >> (8 * digit)) & 0xff);
        for (int i = 0; i < length; i++) {
            //这里有两种方法来实现高位到低位或者低位到高位的编码，具体选择哪种待评估
//			if (i == length -1) {
//				result[i] = (byte) ((value >> (8 * (i - 1))) & 0x7f);
//			} else {
//				result[i] = (byte) ((value >> (8 * (i - 1))) & 0xff | 0x80);
//			}
            result[i] = (byte) ((value >> (8 * (digit - i))) & 0xff);//高位到低位
        }
        return result;
    }

    /**
     * 一个字节8位最大为127
     * @param value
     * @return
     */
    private static double computeTagDigit(double value) {
        if (value < 0x1f) {
            throw new IllegalArgumentException(
                    "the tag value must not less than 31.");
        }
        return Math.ceil(log(value + 1, 128));
    }

    /**
     * 对数计算换底公式
     *
     * @param value
     * @param base
     * @return
     */
    private static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }
}
