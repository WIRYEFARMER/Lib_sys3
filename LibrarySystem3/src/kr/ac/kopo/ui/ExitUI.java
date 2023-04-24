package kr.ac.kopo.ui;

public class ExitUI extends BaseUI {
	
	@Override
	public void execute() throws Exception{
		System.out.println("----------------------------------");
		System.out.println("\t도서관 시스템 종료");
		System.out.println("----------------------------------");
		System.exit(0);
		
	}

}
