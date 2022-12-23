package tools.Graph;

public class GraphNode {
    public int id;
    public String label;
    public String name;
    public String filename;
    public boolean overrided;
    public boolean invoke_call;
    public boolean call_call;
    public boolean offset_get;
    public boolean current_call;
    public boolean to_string;
    public boolean get_set_call;
    public boolean arb_obj_instance;
    public boolean vuln;

    public GraphNode(int id, String label, String name, String filename, boolean overrided, boolean invokeCall, boolean callCall, boolean offsetGet, boolean currentCall, boolean toString, boolean getSetCall, boolean arbObjInstance, boolean vuln) {
        this.id=id;
        this.label = label;
        this.name = name;
        this.filename = filename;
        this.overrided = overrided;
        this.invoke_call = invokeCall;
        this.call_call = callCall;
        this.offset_get = offsetGet;
        this.current_call = currentCall;
        this.to_string = toString;
        this.get_set_call = getSetCall;
        this.arb_obj_instance = arbObjInstance;
        this.vuln = vuln;
    }
}
