// automatically generated by the FlatBuffers compiler, do not modify

package ddlog.__analyze;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class __BigInt extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_2_0_0(); }
  public static __BigInt getRootAs__BigInt(ByteBuffer _bb) { return getRootAs__BigInt(_bb, new __BigInt()); }
  public static __BigInt getRootAs__BigInt(ByteBuffer _bb, __BigInt obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public __BigInt __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public boolean sign() { int o = __offset(4); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public int bytes(int j) { int o = __offset(6); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int bytesLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteVector bytesVector() { return bytesVector(new ByteVector()); }
  public ByteVector bytesVector(ByteVector obj) { int o = __offset(6); return o != 0 ? obj.__assign(__vector(o), bb) : null; }
  public ByteBuffer bytesAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer bytesInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }

  public static int create__BigInt(FlatBufferBuilder builder,
      boolean sign,
      int bytesOffset) {
    builder.startTable(2);
    __BigInt.addBytes(builder, bytesOffset);
    __BigInt.addSign(builder, sign);
    return __BigInt.end__BigInt(builder);
  }

  public static void start__BigInt(FlatBufferBuilder builder) { builder.startTable(2); }
  public static void addSign(FlatBufferBuilder builder, boolean sign) { builder.addBoolean(0, sign, false); }
  public static void addBytes(FlatBufferBuilder builder, int bytesOffset) { builder.addOffset(1, bytesOffset, 0); }
  public static int createBytesVector(FlatBufferBuilder builder, byte[] data) { return builder.createByteVector(data); }
  public static int createBytesVector(FlatBufferBuilder builder, ByteBuffer data) { return builder.createByteVector(data); }
  public static void startBytesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static int end__BigInt(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public __BigInt get(int j) { return get(new __BigInt(), j); }
    public __BigInt get(__BigInt obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

