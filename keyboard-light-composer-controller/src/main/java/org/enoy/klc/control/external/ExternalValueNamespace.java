package org.enoy.klc.control.external;

public class ExternalValueNamespace {

    private String scope;
    private String identifier;

    public ExternalValueNamespace(String scope, String identifier) {
        this.scope = scope;
        this.identifier = identifier;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExternalValueNamespace that = (ExternalValueNamespace) o;

        if (scope != null ? !scope.equals(that.scope) : that.scope != null) return false;
        return identifier != null ? identifier.equals(that.identifier) : that.identifier == null;
    }

    @Override
    public int hashCode() {
        int result = scope != null ? scope.hashCode() : 0;
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        return result;
    }

	@Override
	public String toString() {
		return "ExternalValueNamespace [scope=" + scope + ", identifier=" + identifier + "]";
	}
    
}
