package test.com.eyckwu.tlv;

import com.eyckwu.tlv.TLVElement;
import com.eyckwu.tlv.TLVEncoder;
import com.eyckwu.utils.Log;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Arrays;

/**
 * TLVEncoder Tester.
 *
 * @author <Authors name>
 * @since <pre>ʮ�� 7, 2019</pre>
 * @version 1.0
 */
public class TLVEncoderTest {
    private static final String TAG = "TLVEncoderTest";

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: encodeTag2(int frameType, int dataType, int tagValue)
     *
     */
    @Test
    public void testEncodeTag2() throws Exception {
        byte[] bytes = TLVEncoder.encodeTag2(TLVElement.Tag.PRIMITIVE_FRAME, TLVElement.Tag.PRIMITIVE_DATA, 32);
        Log.d(TAG, "testEncodeTag2: bytes = " + Arrays.toString(bytes));
    }

    /**
     *
     * Method: log(double value, double base)
     *
     */
    @Test
    public void testLog() throws Exception {

    }


    /**
     *
     * Method: encodeTag(int frameType, int dataType, int tagValue)
     *
     */
    @Test
    public void testEncodeTag() throws Exception {

    }

    /**
     *
     * Method: encodeValueFromLowToHighBit(int result, int digit, int value)
     *
     */
    @Test
    public void testEncodeValueFromLowToHighBit() throws Exception {

    }

    /**
     *
     * Method: intToByteArray(int value, int digit)
     *
     */
    @Test
    public void testIntToByteArray() throws Exception {

    }

    /**
     *
     * Method: computeTagDigit(double value)
     *
     */
    @Test
    public void testComputeTagDigit() throws Exception {
//        double v32 = TLVEncoder.computeTagDigit(128);
//        Log.d(TAG, "testComputeTagDigit: v32 = " + v32);
    }

} 
