package jserver;

public class REF<t> {
	public t obj;
	public REF<t> thisobj = this;
	public REF(t o) {
		ref(o);
	}
	public REF() {
		
	}
	public void ref(t o) {
		obj = o;
	}
	public void deref() {
		obj = null;
	}
}
