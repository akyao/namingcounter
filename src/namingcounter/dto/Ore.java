package namingcounter.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;

public class Ore {

	Map<String, OreOre> oreDayo = new HashMap<>();
	
	public void add(List<String> words, IJavaElement elementType) {
		for (String word : words) {
			OreOre oreore = oreDayo.containsKey(word) 
					? oreDayo.get(word)
					: new OreOre();
			if ( elementType instanceof IType) {
				// TODO class, interface, enum?
				oreore.countInClass += 1;
			} else if ( elementType instanceof IMethod){
				oreore.countInMethod += 1;
			} else if (elementType instanceof IField) {
				oreore.countInField += 1;
			} else {
				oreore.countInSomething += 1;
			}
			oreDayo.put(word, oreore);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Entry<String, OreOre> entry : oreDayo.entrySet()) {
			sb.append(String.format("%s:{%s}\n", entry.getKey(), entry.getValue()));
		}
		sb.append("}");
		return sb.toString();
	}
	
	public static class OreOre {
		public int countInClass;
		public int countInMethod;
		public int countInField;
		public int countInSomething; // what?
		
		public int total() {
			return countInClass + countInMethod + countInField + countInSomething;
		}
		
		@Override
		public String toString() {
			return String.format("%s,%s,%s,%s", countInClass, countInMethod, countInField, countInSomething);
		}
	}
}
