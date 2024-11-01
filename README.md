# 프로젝트 명 : 오늘의 가계부

## 기능 설명

  1. 가계부 작성 : 이용자는 수입 혹은 지출에 관한 내역을 날짜, 수입 or 지출, 카테고리 입력, 금액, 메모를 순차적으로 입력하면 해당 데이터가 저장됩니다.

     *날짜, 카테고리 입력시 올바르게 입력하지 않으면 올바른 입력을 할 때까지 반복문을 사용했습니다.
  3. 내역 조회 : 1번에서 작성한 내용을 바탕으로 수입은 상단에, 지출은 하단에 순차적으로 기록이 되며 이를 한 눈에 확인 할 수 있습니다.
  4. 자산 현황 : 1번에서 작성한 내용을 바탕으로 이용자의 총 자산을 확인 할 수 있습니다. 총 수입, 총 지출, 지출 카테고리별 그래프를 통해 지출 내역을 좀 더 쉽게 확인 할 수 있습니다.
  5. 내역 수정 : 기존에 작성한 내역을 수정할 수 있습니다. 기존 내역에서 수정하고 싶은 번호를 입력한 뒤 1번에서 작성한 대로 순차적으로 정보를 입력하고 저장되면 해당 데이터는 가장 아래쪽에 저장이 됩니다.
  6. 내역 삭제 : 기존에 작성한 내역을 삭제할 수 있습니다. 기존 내역에서 삭제하고 싶은 번호를 입력하면 해당 내역은 목록에서 제거됩니다.
  7. 통합 저장 : 1, 4, 5번 메뉴에서 새롭게 추가, 수정, 삭제한 이후 통합 저장을 눌러야만 프로그램을 종료하고 다시 실행했을 때 이전 기록을 불러올 수 있습니다.
  8. 내역 초기화 : 저장해둔 기록들을 초기화 시킵니다. 해당 기능을 사용하면 6번의 실행과 상관없이 모든 데이터가 삭제 됩니다.
  9. 종료 : 프로그램이 종료됩니다. 

## UML 클래스 다이어그램
![클래스다이어그램](https://github.com/SuLim0813/Java_BudgetManager/blob/main/UML%20%ED%81%B4%EB%9E%98%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.jpg)

## 구현화면

●프로그램 첫 화면 & 메뉴바 표시

![image](https://github.com/user-attachments/assets/5b611c45-5d7f-4844-8077-cb307e9272c5)

●가계부 작성 화면(수입 or 지출에 따른 카테고리 세분화)

![image](https://github.com/user-attachments/assets/ac7ddf35-5b31-4075-b13c-573fed707695)
![image](https://github.com/user-attachments/assets/3f98e629-6511-49a1-bb42-1019e9be0e40)

●작성 중 날짜 오기입, 카테고리 오기입 했을 때

![image](https://github.com/user-attachments/assets/02783c7e-614a-4ff9-8020-8c71e22b8445)
![image](https://github.com/user-attachments/assets/d459857d-e692-4657-a75a-acd46e01dc18)


●가계부 내역 조회

![image](https://github.com/user-attachments/assets/fad563ae-cdad-48b0-966a-440ef777f3d5)

●자산 현황보기

![image](https://github.com/user-attachments/assets/f32c3cbb-51d1-42fa-a724-1892dc66d5b5)

●가계부 내역 수정하기(기존 번호 외에 다른 번호를 입력할 시 다시 번호를 입력하는 멘트)

![image](https://github.com/user-attachments/assets/95b82f5c-0869-4558-97bf-1577e8e8ab41)
![image](https://github.com/user-attachments/assets/db7c0150-d8b0-4912-8c2b-5ec204137121)

●수정 이후 내역 조회

![image](https://github.com/user-attachments/assets/61e1fa3a-a430-43ce-aa16-18c47ab286f3)

●가계부 내역 삭제하기(기존 번호 외에 다른 번호를 입력할 시 다시 번호를 입력하라는 문구 뜸)

![image](https://github.com/user-attachments/assets/a76aeb19-085c-48a8-a865-fd2e91d1f9c7)
![image](https://github.com/user-attachments/assets/fe00effa-eb5f-41ba-8442-6ec55a60db5c)

●삭제 이후 내역 조회

![image](https://github.com/user-attachments/assets/32ef25d5-7734-4384-862d-1b76402cfe53)

●통합 저장하기

![image](https://github.com/user-attachments/assets/4e71252e-ea2d-4603-b9ad-99cfadee309c)

●프로그램 종료

![image](https://github.com/user-attachments/assets/df8c20bb-0c40-43f0-9d35-8fa154ce30bc)

