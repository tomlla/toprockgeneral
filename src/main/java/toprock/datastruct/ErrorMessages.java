package toprock.datastruct;

import static java.util.stream.Collectors.*;

import java.util.*;
import java.util.stream.*;

import lombok.*;

@RequiredArgsConstructor
public class ErrorMessages implements Collection<ErrorMessages.ErrorEntry> {
    
    private final List<ErrorEntry> errors = new ArrayList<>();
    
    @RequiredArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class ErrorEntry {
        public final String key;
        public final String value;
        
        public String displayName; // nullable
        
        public String toLogString() {
            return String.format("{key: %s, displayName: %s, value: %s}", key, displayName, value);
        }
    }

    public boolean add(final String key, final String value) {
        return errors.add(new ErrorEntry(key, value));
    }
    
    public boolean add(final String key, final String value, final String displayName) {
        return errors.add(new ErrorEntry(key, value));
    }

    @Override
    public boolean add(final ErrorEntry e) {
        return errors.add(e);
    }

    @Override
    public Stream<ErrorEntry> stream() {
        return this.errors.stream();
    }
    
    @Override
    public int size() {
        return errors.size();
    }
    
    @Override
    public boolean isEmpty() {
        return errors.size() == 0;
    }
    
    public boolean containsKey(final String findingKey) {
        return stream().anyMatch(e -> e.key.equals(findingKey));
    }
    
    public Set<String> keySet() {
        return errors.stream().map(e -> e.key).collect(Collectors.toSet());
    }
    
    @Override
    public Iterator iterator() {
        return errors.iterator();
    }
    
    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Object[] toArray(final Object[] a) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean remove(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean containsAll(final Collection c) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean addAll(final Collection c) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean removeAll(final Collection c) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean retainAll(final Collection c) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean contains(final Object o) {
        return stream().anyMatch(e -> e.equals(o));
    }
    
    public boolean isOK() {
        return isEmpty();
    }
    
    public boolean isNG() {
        return size() > 0;
    }
    
    public List<String> getMessages(final String key) {
        return stream().filter(e -> e.key.equals(key)).map(e -> e.value).collect(toList());
    }
    
    public String getJoinedMessage(final String key, final String delimiter) {
        return stream().filter(e -> e.key.equals(key))
                       .map(e -> e.value)
                       .collect(joining(delimiter));
    }
    
    // <method name aliases>
    
    public boolean hasError() {
        return isNG();
    }
    
    // </method name aliases>
}
