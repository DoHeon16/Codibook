package software;

public class CCodi {
   String ClotheList[]= new String[4];
   
   public CCodi() {
      // TODO Auto-generated constructor stub
       this("none","none","none","none");
      
   }
   public CCodi(String c1,String c2){
      ClotheList[0] = c1;
      ClotheList[1] = c2;
      ClotheList[2] = "none";
      ClotheList[3] = "none";
   }
   public CCodi(String c1,String c2,String c3){
      ClotheList[0] = c1;
      ClotheList[1] = c2;
      ClotheList[2] = c3;
      ClotheList[3] = "none";
   }
   public CCodi(String c1,String c2,String c3,String c4){
      ClotheList[0] = c1;
      ClotheList[1] = c2;
      ClotheList[2] = c3;
      ClotheList[3] = c4;
   }
   public String[] getClotheList(){
      return ClotheList;
   }
   public String getCCodi() {
      String CCodi = "";
      for(int i = 0; i < 4;i++)
      {
         if(!ClotheList[i].equals("none")) {
            CCodi = CCodi + ClotheList[i];
         }
         else 
            break;
         CCodi = CCodi + "/";
         
      }
      return CCodi;
   }
   
}