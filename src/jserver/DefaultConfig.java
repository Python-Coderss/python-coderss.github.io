package jserver;

public class DefaultConfig implements Config {
	public static DefaultConfig ServerConfig = new DefaultConfig();
	private DefaultConfig() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getServerVersion() {
		// TODO Auto-generated method stub
		return "1.0";
	}

	@Override
	public double servletSpec() {
		// TODO Auto-generated method stub
		return 1.0;
	}

}
