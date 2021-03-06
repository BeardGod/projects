
public class noValue<V> implements Trie<V> {
	Dict<Trie<V>> dict;
	public boolean hasKey(String key){
		if (key.length() == 0) {
			return false;
		} else if(this.dict.hasKey(key.substring(0,1))){
			return this.dict.lookUp(key.substring(0,1)).hasKey(key.substring(1));
		} else return false;
	}
	public V lookUp(String key){
		if (key.length() == 0) {
			throw new RuntimeException("No value associated with the given key");
		} else {
			return this.dict.lookUp(key.substring(0,1)).lookUp(key.substring(1));
		} 
	}
	
	public Trie<V> set(String key, V v) {
		Trie<V> empty = new noValue<V>(new emptyDict<Trie<V>>());
		if (key.length() == 0) {
			return new entry<V>(v, this.dict);
		} else if(this.dict.hasKey(key.substring(0,1))){
			return new noValue<V>(this.dict.set(key.substring(0,1),this.dict.lookUp(key.substring(0,1)).set(key.substring(1), v)));
		} else {
			return new noValue<V>(new consDict<Trie<V>>(key.substring(0,1), empty.set(key.substring(1), v), this.dict));
		}
	}
	
	public noValue(Dict<Trie<V>> dict) {
		this.dict = dict;
	}
}
