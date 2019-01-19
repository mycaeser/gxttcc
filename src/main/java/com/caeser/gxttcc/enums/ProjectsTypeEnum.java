package com.caeser.gxttcc.enums;

public enum ProjectsTypeEnum {
	WEAKCURRENT(1, "弱电工程"), SKYNET(2, "天网工程"), CIRCUIT(3, "线路工程"), MAINTAIN(
			4, "维护工程"),GENERIC_CABING(5, "综合布线工程"),ROOM_POINTS(
					6, "室分工程"),IRON_TOWER(7, "铁塔工程"),RADIO_TELEVISION(
							8, "广电工程"),MOBILE_WIRELESS(9, "移动无线集成工程");
	private int state;

	private String stateInfo;
	
	private ProjectsTypeEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
	public static ProjectsTypeEnum stateOf(int index) {
		for (ProjectsTypeEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(ProjectsTypeEnum.stateOf(1).getStateInfo());
	}
}
