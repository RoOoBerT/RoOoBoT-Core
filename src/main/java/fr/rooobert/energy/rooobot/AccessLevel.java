package fr.rooobert.energy.rooobot;

/** IRC access rights */
public enum AccessLevel {
	USER(0, "USER"),
	VOP(1, "VOP"),
	HOP(2, "HOP"),
	SOP(3, "SOP"),
	FOUNDER(4, "FOUNDER"),
	;
	
	// --- Attributes
	private final int order;
	private final String text;
	
	// 
	private AccessLevel(int order, String name) {
		this.order = order;
		this.text = name;
	}
	
	public boolean lesserThan(AccessLevel pa) {
		return this.order < pa.order;
	}
	
	public boolean greaterThan(AccessLevel pa) {
		return this.order > pa.order;
	}
	
	public static AccessLevel parse(String input) {
		AccessLevel level = null;
		for (AccessLevel access : AccessLevel.values()) {
			if (access.text.equalsIgnoreCase(input)) {
				level = access;
			}
		}
		return level;
	}

	public String getText() {
		return this.text;
	}
}
