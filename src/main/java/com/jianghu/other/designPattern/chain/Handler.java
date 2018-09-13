package com.jianghu.other.designPattern.chain;

public abstract class Handler {
	public final static int FATHER_LEVEL_REQUEST = 1;
	public final static int HUSBAND_LEVEL_REQUEST = 2;
	public final static int SON_LEVEL_REQUEST = 3;

	private int level = 0;

	private Handler nextHander;

	public void setNextHander(Handler nextHander) {
		this.nextHander = nextHander;
	}

	public Handler(int level) {
		super();
		this.level = level;
	}

	//不允许被子类修改
	public final void HandleMessage(IWomen women) {
		if (women.getType() == this.level) {
			this.response(women);
		} else {
			if (this.nextHander != null) {
				this.nextHander.HandleMessage(women);
			} else {
				System.out.println("没有后续处理人了。");
			}
		}
	}

	protected abstract void response(IWomen women);
}
