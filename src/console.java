import java.util.ArrayList;

///Used to write console.log() instead of System.out.println()
public class console {
	///logs whatever object is passed + new line
	static void log(Object obj){
		System.out.println(obj);
	}
	
	static void logSquares(ArrayList<Square> s){
		String out = "Squares: [ ";
		for(int i=0; i<s.size(); i++){
			out+=s.get(i).name+", ";
		}
		out = out.substring(0,out.length()-1);
		out+="]";
		console.log(out);
	}
}
