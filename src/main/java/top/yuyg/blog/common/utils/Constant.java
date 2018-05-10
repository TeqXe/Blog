package top.yuyg.blog.common.utils;

public class Constant {

	public static final int SUPER_ADMIN = 1;

	public enum MenuType {
		
		CATALOG(0),
		
		MENU(1),

		BUTTON(2);

		private int value;

		MenuType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public enum ScheduleStatus {

		NORMAL(0),

		PAUSE(1);

		private int value;

		ScheduleStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
