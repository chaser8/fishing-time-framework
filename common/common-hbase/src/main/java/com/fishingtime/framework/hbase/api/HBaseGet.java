package com.fishingtime.framework.hbase.api;

import lombok.Getter;
import org.apache.hadoop.hbase.client.Consistency;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.IsolationLevel;
import org.apache.hadoop.hbase.client.Row;
import org.apache.hadoop.hbase.exceptions.DeserializationException;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.io.TimeRange;
import org.apache.hadoop.hbase.security.access.Permission;
import org.apache.hadoop.hbase.security.visibility.Authorizations;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-02-27 16:57
 **/
@Getter
public class HBaseGet extends HBaseQuery{
    private Get get;

    public HBaseGet(String tableName,String rowKey){
        super(tableName);
        get =new Get(Bytes.toBytes(rowKey));
    }
    public HBaseGet(String tableName,String family,String rowKey){
        super(tableName);
        get =new Get(Bytes.toBytes(rowKey));
        get.addFamily(Bytes.toBytes(family));
    }
    public boolean isCheckExistenceOnly() {
        return get.isCheckExistenceOnly();
    }

    public Get setCheckExistenceOnly(boolean checkExistenceOnly) {
        return get.setCheckExistenceOnly(checkExistenceOnly);
    }

    public boolean isClosestRowBefore() {
        return get.isClosestRowBefore();
    }

    public Get setClosestRowBefore(boolean closestRowBefore) {
        return get.setClosestRowBefore(closestRowBefore);
    }

    public Get addFamily(byte[] family) {
        return get.addFamily(family);
    }

    public Get addColumn(byte[] family, byte[] qualifier) {
        return get.addColumn(family, qualifier);
    }

    public Get setTimeRange(long minStamp, long maxStamp) throws IOException {
        return get.setTimeRange(minStamp, maxStamp);
    }

    public Get setTimeStamp(long timestamp) throws IOException {
        return get.setTimeStamp(timestamp);
    }

    public Get setMaxVersions() {
        return get.setMaxVersions();
    }

    public Get setMaxVersions(int maxVersions) throws IOException {
        return get.setMaxVersions(maxVersions);
    }

    public Get setMaxResultsPerColumnFamily(int limit) {
        return get.setMaxResultsPerColumnFamily(limit);
    }

    public Get setRowOffsetPerColumnFamily(int offset) {
        return get.setRowOffsetPerColumnFamily(offset);
    }

    public Get setFilter(Filter filter) {
        return get.setFilter(filter);
    }

    public Get setCacheBlocks(boolean cacheBlocks) {
        return get.setCacheBlocks(cacheBlocks);
    }

    public boolean getCacheBlocks() {
        return get.getCacheBlocks();
    }

    public byte[] getRow() {
        return get.getRow();
    }

    public int getMaxVersions() {
        return get.getMaxVersions();
    }

    public int getMaxResultsPerColumnFamily() {
        return get.getMaxResultsPerColumnFamily();
    }

    public int getRowOffsetPerColumnFamily() {
        return get.getRowOffsetPerColumnFamily();
    }

    public TimeRange getTimeRange() {
        return get.getTimeRange();
    }

    public Set<byte[]> familySet() {
        return get.familySet();
    }

    public int numFamilies() {
        return get.numFamilies();
    }

    public boolean hasFamilies() {
        return get.hasFamilies();
    }

    public Map<byte[], NavigableSet<byte[]>> getFamilyMap() {
        return get.getFamilyMap();
    }

    public Map<String, Object> getFingerprint() {
        return get.getFingerprint();
    }

    public Map<String, Object> toMap(int maxCols) {
        return get.toMap(maxCols);
    }

    public int compareTo(Row other) {
        return get.compareTo(other);
    }

    public Get setAttribute(String name, byte[] value) {
        return get.setAttribute(name, value);
    }

    public Get setId(String id) {
        return get.setId(id);
    }

    public Get setAuthorizations(Authorizations authorizations) {
        return get.setAuthorizations(authorizations);
    }

    public Get setACL(Map<String, Permission> perms) {
        return get.setACL(perms);
    }

    public Get setACL(String user, Permission perms) {
        return get.setACL(user, perms);
    }

    public Get setConsistency(Consistency consistency) {
        return get.setConsistency(consistency);
    }

    public Get setReplicaId(int id) {
        return get.setReplicaId(id);
    }

    public Get setIsolationLevel(IsolationLevel level) {
        return get.setIsolationLevel(level);
    }

    public Filter getFilter() {
        return get.getFilter();
    }

    public Authorizations getAuthorizations() throws DeserializationException {
        return get.getAuthorizations();
    }

    public byte[] getACL() {
        return get.getACL();
    }

    public Consistency getConsistency() {
        return get.getConsistency();
    }

    public int getReplicaId() {
        return get.getReplicaId();
    }

    public IsolationLevel getIsolationLevel() {
        return get.getIsolationLevel();
    }

    public byte[] getAttribute(String name) {
        return get.getAttribute(name);
    }

    public Map<String, byte[]> getAttributesMap() {
        return get.getAttributesMap();
    }

    public String getId() {
        return get.getId();
    }

    public Map<String, Object> toMap() {
        return get.toMap();
    }

    public String toJSON(int maxCols) throws IOException {
        return get.toJSON(maxCols);
    }

    public String toJSON() throws IOException {
        return get.toJSON();
    }

    public String toString(int maxCols) {
        return get.toString(maxCols);
    }
}
