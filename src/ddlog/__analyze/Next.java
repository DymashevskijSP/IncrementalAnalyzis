// automatically generated by the FlatBuffers compiler, do not modify

package ddlog.__analyze;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Next extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_2_0_0(); }
  public static Next getRootAsNext(ByteBuffer _bb) { return getRootAsNext(_bb, new Next()); }
  public static Next getRootAsNext(ByteBuffer _bb, Next obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public Next __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public ddlog.__analyze.__BigInt v() { return v(new ddlog.__analyze.__BigInt()); }
  public ddlog.__analyze.__BigInt v(ddlog.__analyze.__BigInt obj) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }
  public ddlog.__analyze.__BigInt nxt() { return nxt(new ddlog.__analyze.__BigInt()); }
  public ddlog.__analyze.__BigInt nxt(ddlog.__analyze.__BigInt obj) { int o = __offset(6); return o != 0 ? obj.__assign(__indirect(o + bb_pos), bb) : null; }

  public static int createNext(FlatBufferBuilder builder,
      int vOffset,
      int nxtOffset) {
    builder.startTable(2);
    Next.addNxt(builder, nxtOffset);
    Next.addV(builder, vOffset);
    return Next.endNext(builder);
  }

  public static void startNext(FlatBufferBuilder builder) { builder.startTable(2); }
  public static void addV(FlatBufferBuilder builder, int vOffset) { builder.addOffset(0, vOffset, 0); }
  public static void addNxt(FlatBufferBuilder builder, int nxtOffset) { builder.addOffset(1, nxtOffset, 0); }
  public static int endNext(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public Next get(int j) { return get(new Next(), j); }
    public Next get(Next obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

