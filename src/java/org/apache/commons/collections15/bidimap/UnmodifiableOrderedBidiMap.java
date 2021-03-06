// GenericsNote: Converted.
/*
 *  Copyright 2003-2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.commons.collections15.bidimap;

import org.apache.commons.collections15.*;
import org.apache.commons.collections15.iterators.UnmodifiableOrderedMapIterator;
import org.apache.commons.collections15.map.UnmodifiableEntrySet;
import org.apache.commons.collections15.set.UnmodifiableSet;

import java.util.Map;
import java.util.Set;

/**
 * Decorates another <code>OrderedBidiMap</code> to ensure it can't be altered.
 *
 * @author Matt Hall, John Watkinson, Stephen Colebourne
 * @version $Revision: 1.1 $ $Date: 2005/10/11 17:05:19 $
 * @since Commons Collections 3.0
 */
public final class UnmodifiableOrderedBidiMap <K,V> extends AbstractOrderedBidiMapDecorator<K, V> implements Unmodifiable {

    /**
     * The inverse unmodifiable map
     */
    private UnmodifiableOrderedBidiMap<V, K> inverse;

    /**
     * Factory method to create an unmodifiable map.
     * <p/>
     * If the map passed in is already unmodifiable, it is returned.
     *
     * @param map the map to decorate, must not be null
     * @return an unmodifiable OrderedBidiMap
     * @throws IllegalArgumentException if map is null
     */
    public static <K,V> OrderedBidiMap<K, V> decorate(OrderedBidiMap<K, V> map) {
        if (map instanceof Unmodifiable) {
            return map;
        }
        return new UnmodifiableOrderedBidiMap<K, V>(map);
    }

    //-----------------------------------------------------------------------
    /**
     * Constructor that wraps (not copies).
     *
     * @param map the map to decorate, must not be null
     * @throws IllegalArgumentException if map is null
     */
    private UnmodifiableOrderedBidiMap(OrderedBidiMap<K, V> map) {
        super(map);
    }

    //-----------------------------------------------------------------------
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map<? extends K, ? extends V> mapToCopy) {
        throw new UnsupportedOperationException();
    }

    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = super.entrySet();
        return UnmodifiableEntrySet.decorate(set);
    }

    public Set<K> keySet() {
        Set<K> set = super.keySet();
        return UnmodifiableSet.decorate(set);
    }

    public Set<V> values() {
        Set<V> coll = super.values();
        return UnmodifiableSet.decorate(coll);
    }

    //-----------------------------------------------------------------------
    public K removeValue(Object value) {
        throw new UnsupportedOperationException();
    }

    public MapIterator<K, V> mapIterator() {
        return orderedMapIterator();
    }

    public BidiMap<V, K> inverseBidiMap() {
        return inverseOrderedBidiMap();
    }

    //-----------------------------------------------------------------------
    public OrderedMapIterator<K, V> orderedMapIterator() {
        OrderedMapIterator<K, V> it = getOrderedBidiMap().orderedMapIterator();
        return UnmodifiableOrderedMapIterator.decorate(it);
    }

    public OrderedBidiMap<V, K> inverseOrderedBidiMap() {
        if (inverse == null) {
            inverse = new UnmodifiableOrderedBidiMap<V, K>(getOrderedBidiMap().inverseOrderedBidiMap());
            inverse.inverse = this;
        }
        return inverse;
    }

}
