package org.dizitart.no2.store;

import org.dizitart.no2.meta.Attributes;
import org.h2.mvstore.MVMap;
import org.h2.mvstore.MVStore;

import java.util.Set;

import static org.dizitart.no2.Constants.META_MAP_NAME;

/**
 * A {@link MVStore} backed {@link NitriteStore} implementation.
 *
 * @since 1.0
 * @author Anindya Chatterjee.
 */
public final class NitriteMVStore implements NitriteStore {
    private MVStore mvStore;

    /**
     * Instantiates a new {@link NitriteMVStore}.
     *
     * @param store the store
     */
    public NitriteMVStore(MVStore store) {
        this.mvStore = store;
    }

    @Override
    public Set<String> getMapNames() {
        return mvStore.getMapNames();
    }

    @Override
    public boolean hasUnsavedChanges() {
        return mvStore.hasUnsavedChanges();
    }

    @Override
    public boolean isClosed() {
        return mvStore.isClosed();
    }

    @Override
    public void compactMoveChunks() {
        mvStore.compactMoveChunks();
    }

    @Override
    public void commit() {
        mvStore.commit();
    }

    @Override
    public void close() {
        mvStore.close();
    }

    @Override
    public void closeImmediately() {
        mvStore.closeImmediately();
    }

    @Override
    public boolean hasMap(String mapName) {
        return mvStore.hasMap(mapName);
    }

    @Override
    public <Key, Value> NitriteMap<Key, Value> openMap(String mapName) {
        MVMap<Key, Value> mvMap = mvStore.openMap(mapName);
        return new NitriteMVMap<>(mvMap, this);
    }

    @Override
    public <Key, Value> void removeMap(NitriteMap<Key, Value> map) {
        NitriteMVMap<Key, Value> nitriteMVMap = (NitriteMVMap<Key, Value>) map;
        MVMap<Key, Value> mvMap = nitriteMVMap.getUnderlyingMVMap();
        mvStore.removeMap(mvMap);
    }

    @Override
    public NitriteMap<String, Attributes> metaMap() {
        MVMap<String, Attributes> mvMap = mvStore.openMap(META_MAP_NAME);
        return new NitriteMVMap<>(mvMap, this);
    }
}
