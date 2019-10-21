package com.fishingtime.framework.hbase.api;

import lombok.Getter;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.exceptions.DeserializationException;
import org.apache.hadoop.hbase.security.access.Permission;
import org.apache.hadoop.hbase.security.visibility.CellVisibility;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.UUID;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-02-27 16:55
 **/
@Getter
public class HBaseDelete extends HBaseOperationsParams {
    private Delete delete=null;
    public HBaseDelete(String tableName,String rowKey){
        super(tableName);
        delete = new Delete(Bytes.toBytes(rowKey));
    }
    public Delete addDeleteMarker(Cell kv) throws IOException {
        return delete.addDeleteMarker(kv);
    }

    @Deprecated
    public Delete deleteFamily(byte[] family) {
        return delete.deleteFamily(family);
    }

    public Delete addFamily(byte[] family) {
        return delete.addFamily(family);
    }

    @Deprecated
    public Delete deleteFamily(byte[] family, long timestamp) {
        return delete.deleteFamily(family, timestamp);
    }

    public Delete addFamily(byte[] family, long timestamp) {
        return delete.addFamily(family, timestamp);
    }

    @Deprecated
    public Delete deleteFamilyVersion(byte[] family, long timestamp) {
        return delete.deleteFamilyVersion(family, timestamp);
    }

    public Delete addFamilyVersion(byte[] family, long timestamp) {
        return delete.addFamilyVersion(family, timestamp);
    }

    @Deprecated
    public Delete deleteColumns(byte[] family, byte[] qualifier) {
        return delete.deleteColumns(family, qualifier);
    }

    public Delete addColumns(byte[] family, byte[] qualifier) {
        return delete.addColumns(family, qualifier);
    }

    @Deprecated
    public Delete deleteColumns(byte[] family, byte[] qualifier, long timestamp) {
        return delete.deleteColumns(family, qualifier, timestamp);
    }

    public Delete addColumns(byte[] family, byte[] qualifier, long timestamp) {
        return delete.addColumns(family, qualifier, timestamp);
    }

    @Deprecated
    public Delete deleteColumn(byte[] family, byte[] qualifier) {
        return delete.deleteColumn(family, qualifier);
    }

    public Delete addColumn(byte[] family, byte[] qualifier) {
        return delete.addColumn(family, qualifier);
    }

    @Deprecated
    public Delete deleteColumn(byte[] family, byte[] qualifier, long timestamp) {
        return delete.deleteColumn(family, qualifier, timestamp);
    }

    public Delete addColumn(byte[] family, byte[] qualifier, long timestamp) {
        return delete.addColumn(family, qualifier, timestamp);
    }

    public Delete setTimestamp(long timestamp) {
        return delete.setTimestamp(timestamp);
    }

    public Map<String, Object> toMap(int maxCols) {
        return delete.toMap(maxCols);
    }

    public Delete setAttribute(String name, byte[] value) {
        return delete.setAttribute(name, value);
    }

    public Delete setId(String id) {
        return delete.setId(id);
    }

    @Deprecated
    public Delete setWriteToWAL(boolean write) {
        return delete.setWriteToWAL(write);
    }

    public Delete setDurability(Durability d) {
        return delete.setDurability(d);
    }

    public Delete setFamilyCellMap(NavigableMap<byte[], List<Cell>> map) {
        return delete.setFamilyCellMap(map);
    }

    @Deprecated
    public Delete setFamilyMap(NavigableMap<byte[], List<KeyValue>> map) {
        return delete.setFamilyMap(map);
    }

    public Delete setClusterIds(List<UUID> clusterIds) {
        return delete.setClusterIds(clusterIds);
    }

    public Delete setCellVisibility(CellVisibility expression) {
        return delete.setCellVisibility(expression);
    }

    public Delete setACL(String user, Permission perms) {
        return delete.setACL(user, perms);
    }

    public Delete setACL(Map<String, Permission> perms) {
        return delete.setACL(perms);
    }

    public Delete setTTL(long ttl) {
        return delete.setTTL(ttl);
    }

    public CellScanner cellScanner() {
        return delete.cellScanner();
    }

    public Map<String, Object> getFingerprint() {
        return delete.getFingerprint();
    }

    @Deprecated
    public boolean getWriteToWAL() {
        return delete.getWriteToWAL();
    }

    public Durability getDurability() {
        return delete.getDurability();
    }

    public NavigableMap<byte[], List<Cell>> getFamilyCellMap() {
        return delete.getFamilyCellMap();
    }

    @Deprecated
    public NavigableMap<byte[], List<KeyValue>> getFamilyMap() {
        return delete.getFamilyMap();
    }

    public boolean isEmpty() {
        return delete.isEmpty();
    }

    public byte[] getRow() {
        return delete.getRow();
    }

    public int compareTo(Row d) {
        return delete.compareTo(d);
    }

    public long getTimeStamp() {
        return delete.getTimeStamp();
    }

    public List<UUID> getClusterIds() {
        return delete.getClusterIds();
    }

    public CellVisibility getCellVisibility() throws DeserializationException {
        return delete.getCellVisibility();
    }

    public int size() {
        return delete.size();
    }

    public int numFamilies() {
        return delete.numFamilies();
    }

    public long heapSize() {
        return delete.heapSize();
    }

    public byte[] getACL() {
        return delete.getACL();
    }

    public long getTTL() {
        return delete.getTTL();
    }

    public byte[] getAttribute(String name) {
        return delete.getAttribute(name);
    }

    public Map<String, byte[]> getAttributesMap() {
        return delete.getAttributesMap();
    }

    public String getId() {
        return delete.getId();
    }

    public Map<String, Object> toMap() {
        return delete.toMap();
    }

    public String toJSON(int maxCols) throws IOException {
        return delete.toJSON(maxCols);
    }

    public String toJSON() throws IOException {
        return delete.toJSON();
    }

    public String toString(int maxCols) {
        return delete.toString(maxCols);
    }



}