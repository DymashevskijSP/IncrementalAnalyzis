// automatically generated by the FlatBuffers compiler, do not modify

package ddlog.__analyze;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class __String extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_2_0_0(); }
  public static __String getRootAs__String(ByteBuffer _bb) { return getRootAs__String(_bb, new __String()); }
  public static __String getRootAs__String(ByteBuffer _bb, __String obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public __String __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public String v() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer vAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer vInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }

  public static int create__String(FlatBufferBuilder builder,
      int vOffset) {
    builder.startTable(1);
    __String.addV(builder, vOffset);
    return __String.end__String(builder);
  }

  public static void start__String(FlatBufferBuilder builder) { builder.startTable(1); }
  public static void addV(FlatBufferBuilder builder, int vOffset) { builder.addOffset(0, vOffset, 0); }
  public static int end__String(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public __String get(int j) { return get(new __String(), j); }
    public __String get(__String obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

