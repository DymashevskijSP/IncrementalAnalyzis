// Automatically generated by the DDlog compiler.
package ddlog.analyze;
import com.google.flatbuffers.*;
public final class NodeReader
{
    protected NodeReader(ddlog.__analyze.Node inner) { this.inner = inner; }
    private ddlog.__analyze.Node inner;
    public java.math.BigInteger id ()
    {
        return new java.math.BigInteger(this.inner.id().sign() ? 1 : -1, analyzeUpdateParser.byteBufferToArray(this.inner.id().bytesAsByteBuffer()));
    }
    public String label ()
    {
        return this.inner.label();
    }
    public String name ()
    {
        return this.inner.name();
    }
    public String filename ()
    {
        return this.inner.filename();
    }
    public boolean overrided ()
    {
        return this.inner.overrided();
    }
    public boolean invoke_call ()
    {
        return this.inner.invokeCall();
    }
    public boolean call_call ()
    {
        return this.inner.callCall();
    }
    public boolean offset_get ()
    {
        return this.inner.offsetGet();
    }
    public boolean current_call ()
    {
        return this.inner.currentCall();
    }
    public boolean to_strings ()
    {
        return this.inner.toStrings();
    }
    public boolean get_set_call ()
    {
        return this.inner.getSetCall();
    }
    public boolean arb_obj_instance ()
    {
        return this.inner.arbObjInstance();
    }
    public boolean vuln ()
    {
        return this.inner.vuln();
    }
}