package ReportTest;
import java.util.Scanner;
public class PresentProject {
	public static void main(String[] args) {
		// 작동 flag를 run으로 선언. 
		boolean run = true;
		// string 객체 선언
		String[][] myBoard;
		//게시판 목록수 : boardNum개 게시판 항목수 : 5개
		// {index, title, contents, writer, hit}
		int boardNum = 5; // 최대 게시물 개수
		int boardAtr = 5; // 게시물 속성값 수
		myBoard = new String[boardNum][boardAtr];
		Scanner scanner = new Scanner(System.in);
		//게시물 번호부여 1부터 시작
		int count = 1;
		//현재 게시물 수 0개 부터 시작 
		int list = 0; 

		/**
		 *  true환경에선 계속 작동하면서 메뉴 화면 출력, false 환경에선 작동 종료
		 */  
		while(run) {

			//출력 메뉴 화면
			System.out.println("----------------------------------------------");
			System.out.println("1.목록 | 2.생성 | 3.읽기 | 4.수정 | 5.삭제 | 6. 종료");
			System.out.println("----------------------------------------------");
			System.out.print("메뉴 선택> ");

			//메뉴 선택 번호
			String check = scanner.nextLine();
			if(!check.equals("1")&&!check.equals("2")&&!check.equals("3")&&!check.equals("4")&&!check.equals("5")&&!check.equals("6")) {
				System.out.println("잘못된 입력방식입니다.");
				continue;		//어차피 무한 반복이라 메뉴 선택하라는문이 다시 나옴.
			}

			int selectNo = Integer.parseInt(check);	//입력받은 메뉴 번호를 정수형으로 변환
			/*
			 * 1. 목록 출력 기능.
			 */
			if(selectNo == 1) {
				System.out.println("----------------------------------------------");
				System.out.println("번호\t" +"제목"+"\t내용"+ "\t글쓴이"+ "\t조회수\t");
				System.out.println("----------------------------------------------");

				//int selectIndex = 0;// index null 확인용
				int maxCount = count; //현재 게시물 번호 저장

				if(list==0) { //현재 게시물 수 0 개
					System.out.println("내용이 비어있습니다. 2번 생성을 눌러 게시물을 추가하세요.");
				}
				else { //현재 게시물이 0개가 아닐 때
					for(int i=count;i>0;i--) {//최근 게시물 번호부터 시작해서 1까지
						for(int j=0;j<myBoard.length;j++) { //0부터 boardNum개: 최대 길이까지 확인용
							if(myBoard[j][0]!=null && myBoard[j][0].equals(""+i)) { // 둘 다 만족해야해서 삭제한 목록이 있을경우에는 null이 들어가 이 조건을 만족 못함 
								System.out.println(myBoard[j][0] + "\t" + myBoard[j][1] //(count가 생성될때마다 증가하고 삭제목록이 있어도 count)
										+ "\t" + myBoard[j][2]+ "\t" + myBoard[j][3]	//count가 생성될때마다 증가하고 삭제목록이 있어도 count에는 삭제된 번호도 남아 있으므로, 인덱스에 null이 있는지 여기서 같이 확인 함
												+ "\t" + myBoard[j][4] );
							}
							else {
								continue;
							}
						}
					}
				}
			}
			/*
			 * 2. 생성 기능 
			 */
			else if(selectNo == 2) {

				/**
				 * boardNum칸 다 찼을 경우
				 */
				if(list == myBoard.length) { //현재 게시물수가 boardNum개 인경우
					System.out.println("게시글이 꽉 찼습니다.");
					continue;
				}
				// 임시 저장소 선언
				String tempTitle, tempContent, tempWriter;
				// 임시저장소에 저장
				System.out.print("제목> ");
				tempTitle = scanner.nextLine();
				System.out.print("내용> ");
				tempContent= scanner.nextLine();
				System.out.print("글쓴이> ");
				tempWriter= scanner.nextLine();

				for(int i=0;i<myBoard.length;i++) {
					if(myBoard[i][0] != null) {
						continue;
					}
					else {
						// index 증가 (게시물 번호)
						myBoard[i][0] = ""+count;
						myBoard[i][1] = tempTitle;
						myBoard[i][2] = tempContent;
						myBoard[i][3] = tempWriter;
						myBoard[i][4] = "0";  // 처음 조회수 0  
						count++; //게시물 인덱스 번호 증가
						list++; //현재 게시물 게수 증가
						break;
					}
				}

				/**
				 * 생성부분 추가할부분
				 */
				System.out.println("----------------------------------------------");
				System.out.println("번호\t" +"제목"+"\t내용"+ "\t글쓴이"+ "\t조회수\t");
				System.out.println("----------------------------------------------");
				int maxCount = count; //현재 게시물 인덱스 저장

				if(list==0) { //현재 게시물 수 0 개
					System.out.println("내용이 비어있습니다. 2번 생성을 눌러 게시물을 추가하세요.");
				}
				else { //현재 게시물이 0개가 아닐 때
					for(int i=maxCount;i>0;i--) {//최근 게시물 번호부터 시작해서 1까지
						for(int j=0;j<myBoard.length;j++) { //0부터 boardNum개: 최대 길이까지 확인용
							if(myBoard[j][0]!=null && myBoard[j][0].equals(""+i)) {
								System.out.println(myBoard[j][0] + "\t" + myBoard[j][1] 
										+ "\t" + myBoard[j][2]+ "\t" + myBoard[j][3]
												+ "\t" + myBoard[j][4] );
							}
							else {
								continue;
							}
						}
					}
				}

			}
			/**
			 * 3. 읽기 기능 
			 */
			else if(selectNo == 3) {
				if(list==0) {
					System.out.println("내용이 비어있습니다. 2번 생성을 눌러 게시물을 추가하세요.");
					continue;
				}
				System.out.print("번호> ");
				//번호 index 
				String temp = scanner.nextLine();

				int selectIndex = 0;
				while(selectIndex<myBoard.length){
					if(myBoard[selectIndex][0]!=null && myBoard[selectIndex][0].equals(temp)) {
						break;
					}
					selectIndex++;
				}

				if(selectIndex==boardNum) {
					System.out.println("찾는 숫자가 없습니다.");
					continue;
				}


				if(myBoard[selectIndex][0] != null) {
					int tempHit; // 임시 조회수저장소
					tempHit = Integer.parseInt(myBoard[selectIndex][4])+1; // 조회수 1 증가
					myBoard[selectIndex][4] = "" + tempHit;              // 스트링 변환
					System.out.println("제목: "+ myBoard[selectIndex][1]);
					System.out.println("내용: "+ myBoard[selectIndex][2]);
					System.out.println("글쓴이: "+ myBoard[selectIndex][3]);
					System.out.println("조회수: " + myBoard[selectIndex][4]);
				}
				else {
					System.out.println("찾는 번호의 게시물이 없습니다.");
				}


			}
			/**
			 * 4. 수정 기능
			 */
			else if(selectNo == 4) {
				if(list==0) {
					System.out.println("내용이 비어있습니다. 2번 생성을 눌러 게시물을 추가하세요.");
					continue;
				}
				System.out.print("번호> ");
				//번호 index 
				String temp = scanner.nextLine();
				String tempTitle;
				String tempContent;
				//int selectIndex = Integer.parseInt(temp) - 1;

				int selectIndex = 0;
				while(selectIndex<myBoard.length){
					if(myBoard[selectIndex][0]!=null && myBoard[selectIndex][0].equals(temp)) {
						break;
					}
					selectIndex++;
				}
				if(selectIndex==boardNum) {
					System.out.println("찾는 숫자가 없습니다.");
					continue;
				}

				if(myBoard[selectIndex][0].equals(temp)) {
					System.out.println("기존 제목: "+ myBoard[selectIndex][1]); // 기존 제목
					System.out.print("수정 제목> ");
					tempTitle = scanner.nextLine(); // 수정된 제목 입력받음

					System.out.println("기존 내용: "+ myBoard[selectIndex][2]); // 기존 내용
					System.out.print("수정 내용> ");
					tempContent = scanner.nextLine(); // 수정된 제목 입력받음
					// 아무것도 입력 안할때 
					if(tempTitle.equals("")) {
						//break;  // 무시
					}
					else {
						myBoard[selectIndex][1] = tempTitle;  // 수정
					}

					if(tempContent.equals("")) {
						//break;  // 무시
					}
					else {
						myBoard[selectIndex][2] = tempContent;  // 수정
					}
				}
				else {
					System.out.println("찾는 번호의 게시물이 없습니다.");
				}
				/**
				 * 생성부분 추가할부분
				 */
				System.out.println("----------------------------------------------");
				System.out.println("번호\t" +"제목"+"\t내용"+ "\t글쓴이"+ "\t조회수\t");
				System.out.println("----------------------------------------------");
				int maxCount = count; //현재 게시물 인덱스 저장

				if(list==0) { //현재 게시물 수 0 개
					System.out.println("내용이 비어있습니다. 2번 생성을 눌러 게시물을 추가하세요.");
				}
				else { //현재 게시물이 0개가 아닐 때
					for(int i=maxCount;i>0;i--) {//최근 게시물 번호부터 시작해서 1까지
						for(int j=0;j<myBoard.length;j++) { //0부터 boardNum개: 최대 길이까지 확인용
							if(myBoard[j][0]!=null && myBoard[j][0].equals(""+i)) {
								System.out.println(myBoard[j][0] + "\t" + myBoard[j][1] 
										+ "\t" + myBoard[j][2]+ "\t" + myBoard[j][3]
												+ "\t" + myBoard[j][4] );
							}
							else {
								continue;
							}
						}
					}
				}
			}
			/**
			 * 5. 삭제 기능
			 */
			else if(selectNo == 5) {

				if(list==0) {
					System.out.println("내용이 비어있습니다. 2번 생성을 눌러 게시물을 추가하세요.");
					continue;
				}
				System.out.print("번호> ");
				//번호 index 
				String temp = scanner.nextLine();
				//int selectIndex = Integer.parseInt(temp) - 1; // 인덱스값은 -1 차이
				//System.out.println(selectIndex);


				int selectIndex = 0; 
				while(selectIndex<myBoard.length){
					if(myBoard[selectIndex][0]!=null && myBoard[selectIndex][0].equals(temp)) {
						break;
					}
					selectIndex++;
				}
				if(selectIndex==boardNum) {
					System.out.println("찾는 숫자가 없습니다.");
					continue;
				}
				if(myBoard[selectIndex][0] != null) {
					// 모든 항목 null로 다 채우기.
					for(int i=0;i<5;i++) { //5항목
						myBoard[selectIndex][i] = null;
					}
					list--; // 현재 게시물 개수 줄이기
				}
				else {
					System.out.println("찾는 번호의 게시물이 없습니다.");
				}
				/**
				 * 생성부분 추가할부분
				 */
				System.out.println("----------------------------------------------");
				System.out.println("번호\t" +"제목"+"\t내용"+ "\t글쓴이"+ "\t조회수\t");
				System.out.println("----------------------------------------------");
				int maxCount = count; //현재 게시물 인덱스 저장

				if(list==0) { //현재 게시물 수 0 개
					System.out.println("내용이 비어있습니다. 2번 생성을 눌러 게시물을 추가하세요.");
				}
				else { //현재 게시물이 0개가 아닐 때
					for(int i=maxCount;i>0;i--) {//최근 게시물 번호부터 시작해서 1까지
						for(int j=0;j<myBoard.length;j++) { //0부터 boardNum개: 최대 길이까지 확인용
							if(myBoard[j][0]!=null && myBoard[j][0].equals(""+i)) {
								System.out.println(myBoard[j][0] + "\t" + myBoard[j][1] 
										+ "\t" + myBoard[j][2]+ "\t" + myBoard[j][3]
												+ "\t" + myBoard[j][4] );
							}
							else {
								continue;
							}
						}
					}
				}
			}
			/**
			 * 6. 종료 기능 run = true 에서 -> false로 바꾸기 
			 */
			else if(selectNo == 6) {
				run = false;
			}

		}

		System.out.println("프로그램 종료");

	}
}