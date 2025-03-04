package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		
		Connection con = null;
		Statement statement = null;
		
		try {
			// データベースに接続
			con = DriverManager.getConnection(
				"jdbc:mysql://localhost/challenge_java",
				"root",
				// 課題提出のためパスワードはpasswordとします
				"password"
			);
			
			System.out.println("データベース接続成功:" + con );
			
			// SQLクリエを準備する
			statement = con.createStatement();
			String sql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;";
			System.out.println("レコード更新を実行します");
			
			// 点数データを更新する
			int rowCnt = statement.executeUpdate(sql);
			System.out.println( rowCnt + "件のレコードが更新されました");
			
			// 点数順に並べる
			String sqlOrder = "SELECT * FROM scores ORDER BY score_math DESC,score_english DESC";
			ResultSet result = statement.executeQuery(sqlOrder);
			System.out.println("数学・英語の点数が高い順に並べ替えました");
			
			// 並べ替え結果を表示する
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int scoreMath = result.getInt("score_math");
				int scoreEnglish = result.getInt("score_english");
				System.out.println( result.getRow() + "件目:生徒ID=" + id + "／氏名="+ name 
						+ "／数学=" + scoreMath + "／英語=" + scoreEnglish );
			}
			
		}catch(SQLException e) {
			System.out.println("エラー発生:" + e.getMessage());
		}finally {
			//使用したオブジェクトを解放
			if( statement != null) {
				try { statement.close(); } catch(SQLException ignore) {}
			}
			if(  con != null ) {
				try { con.close(); } catch(SQLException ignore) {}
			}
		 }
	}

}



//String sqlOrder = "SELECT score_math, score_english FROM scores ORDER BY "
//		+ "score_math DESC, score_english DESC ";
