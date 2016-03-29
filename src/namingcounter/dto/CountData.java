package namingcounter.dto;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;

/**
 * データ入れ物
 * 
 * @author akyao
 */
public class CountData {

	private Map<String, CountDataItem> items = new HashMap<>();
	
	public void add(List<String> words, IJavaElement elementType) {
		for (String word : words) {
			CountDataItem item = items.containsKey(word) 
					? items.get(word)
					: new CountDataItem(word);
			if ( elementType instanceof IType) {
				// TODO class, interface, enum?
				item.countInClass += 1;
			} else if ( elementType instanceof IMethod){
				item.countInMethod += 1;
			} else if (elementType instanceof IField) {
				item.countInField += 1;
			} else {
				item.countInSomething += 1;
			}
			items.put(word, item);
		}
	}
	
	public List<CountDataItem> getItems() {
		return items.entrySet().stream()
				.map(x -> x.getValue())
				.sorted(Comparator.comparing(CountDataItem::total).reversed())
				.collect(Collectors.toList());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (Entry<String, CountDataItem> entry : items.entrySet()) {
			sb.append(String.format("%s:{%s}\n", entry.getKey(), entry.getValue()));
		}
		sb.append("}");
		return sb.toString();
	}
	
	public static class CountDataItem {
		public String word;
		public int countInClass;
		public int countInMethod;
		public int countInField;
		public int countInSomething; // what?
		
		public CountDataItem(String word) {
			this.word = word;
		}
		
		public int total() {
			return countInClass + countInMethod + countInField + countInSomething;
		}
		
		@Override
		public String toString() {
			return String.format("{%s:[%s,%s,%s,%s]}", word, countInClass, countInMethod, countInField, countInSomething);
		}
	}
}
