package com.fishingtime.framework.hbase.api;

import lombok.Getter;
import lombok.Setter;
import org.apache.hadoop.hbase.client.Consistency;
import org.apache.hadoop.hbase.client.IsolationLevel;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.metrics.ScanMetrics;
import org.apache.hadoop.hbase.exceptions.DeserializationException;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.io.TimeRange;
import org.apache.hadoop.hbase.security.access.Permission;
import org.apache.hadoop.hbase.security.visibility.Authorizations;

import java.io.IOException;
import java.util.Map;
import java.util.NavigableSet;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-02-27 16:55
 **/
@Setter
@Getter
public class HBaseScan extends HBaseQuery {
    private final Scan scan = new Scan();

    public HBaseScan(String tableName) {
        super(tableName);
    }

    public boolean isGetScan() {
        return scan.isGetScan();
    }

    public Scan addFamily(byte[] family) {
        return scan.addFamily(family);
    }

    public Scan addColumn(byte[] family, byte[] qualifier) {
        return scan.addColumn(family, qualifier);
    }

    public Scan setTimeRange(long minStamp, long maxStamp) throws IOException {
        return scan.setTimeRange(minStamp, maxStamp);
    }

    public Scan setTimeStamp(long timestamp) throws IOException {
        return scan.setTimeStamp(timestamp);
    }

    public Scan setStartRow(byte[] startRow) {
        return scan.setStartRow(startRow);
    }

    public Scan setStopRow(byte[] stopRow) {
        return scan.setStopRow(stopRow);
    }

    public Scan setRowPrefixFilter(byte[] rowPrefix) {
        return scan.setRowPrefixFilter(rowPrefix);
    }

    public Scan setMaxVersions() {
        return scan.setMaxVersions();
    }

    public Scan setMaxVersions(int maxVersions) {
        return scan.setMaxVersions(maxVersions);
    }

    public Scan setBatch(int batch) {
        return scan.setBatch(batch);
    }

    public Scan setMaxResultsPerColumnFamily(int limit) {
        return scan.setMaxResultsPerColumnFamily(limit);
    }

    public Scan setRowOffsetPerColumnFamily(int offset) {
        return scan.setRowOffsetPerColumnFamily(offset);
    }

    public Scan setCaching(int caching) {
        return scan.setCaching(caching);
    }

    public long getMaxResultSize() {
        return scan.getMaxResultSize();
    }

    public Scan setMaxResultSize(long maxResultSize) {
        return scan.setMaxResultSize(maxResultSize);
    }

    public Scan setFilter(Filter filter) {
        return scan.setFilter(filter);
    }

    public Scan setFamilyMap(Map<byte[], NavigableSet<byte[]>> familyMap) {
        return scan.setFamilyMap(familyMap);
    }

    public Map<byte[], NavigableSet<byte[]>> getFamilyMap() {
        return scan.getFamilyMap();
    }

    public int numFamilies() {
        return scan.numFamilies();
    }

    public boolean hasFamilies() {
        return scan.hasFamilies();
    }

    public byte[][] getFamilies() {
        return scan.getFamilies();
    }

    public byte[] getStartRow() {
        return scan.getStartRow();
    }

    public byte[] getStopRow() {
        return scan.getStopRow();
    }

    public int getMaxVersions() {
        return scan.getMaxVersions();
    }

    public int getBatch() {
        return scan.getBatch();
    }

    public int getMaxResultsPerColumnFamily() {
        return scan.getMaxResultsPerColumnFamily();
    }

    public int getRowOffsetPerColumnFamily() {
        return scan.getRowOffsetPerColumnFamily();
    }

    public int getCaching() {
        return scan.getCaching();
    }

    public TimeRange getTimeRange() {
        return scan.getTimeRange();
    }

    public Filter getFilter() {
        return scan.getFilter();
    }

    public boolean hasFilter() {
        return scan.hasFilter();
    }

    public Scan setCacheBlocks(boolean cacheBlocks) {
        return scan.setCacheBlocks(cacheBlocks);
    }

    public boolean getCacheBlocks() {
        return scan.getCacheBlocks();
    }

    public Scan setReversed(boolean reversed) {
        return scan.setReversed(reversed);
    }

    public boolean isReversed() {
        return scan.isReversed();
    }

    public Scan setAllowPartialResults(boolean allowPartialResults) {
        return scan.setAllowPartialResults(allowPartialResults);
    }

    public boolean getAllowPartialResults() {
        return scan.getAllowPartialResults();
    }

    public Scan setLoadColumnFamiliesOnDemand(boolean value) {
        return scan.setLoadColumnFamiliesOnDemand(value);
    }

    public Boolean getLoadColumnFamiliesOnDemandValue() {
        return scan.getLoadColumnFamiliesOnDemandValue();
    }

    public boolean doLoadColumnFamiliesOnDemand() {
        return scan.doLoadColumnFamiliesOnDemand();
    }

    public Map<String, Object> getFingerprint() {
        return scan.getFingerprint();
    }

    public Map<String, Object> toMap(int maxCols) {
        return scan.toMap(maxCols);
    }

    public Scan setRaw(boolean raw) {
        return scan.setRaw(raw);
    }

    public boolean isRaw() {
        return scan.isRaw();
    }

    public Scan setSmall(boolean small) {
        return scan.setSmall(small);
    }

    public boolean isSmall() {
        return scan.isSmall();
    }

    public Scan setAttribute(String name, byte[] value) {
        return scan.setAttribute(name, value);
    }

    public Scan setId(String id) {
        return scan.setId(id);
    }

    public Scan setAuthorizations(Authorizations authorizations) {
        return scan.setAuthorizations(authorizations);
    }

    public Scan setACL(Map<String, Permission> perms) {
        return scan.setACL(perms);
    }

    public Scan setACL(String user, Permission perms) {
        return scan.setACL(user, perms);
    }

    public Scan setConsistency(Consistency consistency) {
        return scan.setConsistency(consistency);
    }

    public Scan setReplicaId(int id) {
        return scan.setReplicaId(id);
    }

    public Scan setIsolationLevel(IsolationLevel level) {
        return scan.setIsolationLevel(level);
    }

    public Scan setScanMetricsEnabled(boolean enabled) {
        return scan.setScanMetricsEnabled(enabled);
    }

    public boolean isScanMetricsEnabled() {
        return scan.isScanMetricsEnabled();
    }

    public ScanMetrics getScanMetrics() {
        return scan.getScanMetrics();
    }

    public Authorizations getAuthorizations() throws DeserializationException {
        return scan.getAuthorizations();
    }

    public byte[] getACL() {
        return scan.getACL();
    }

    public Consistency getConsistency() {
        return scan.getConsistency();
    }

    public int getReplicaId() {
        return scan.getReplicaId();
    }

    public IsolationLevel getIsolationLevel() {
        return scan.getIsolationLevel();
    }

    public byte[] getAttribute(String name) {
        return scan.getAttribute(name);
    }

    public Map<String, byte[]> getAttributesMap() {
        return scan.getAttributesMap();
    }

    public String getId() {
        return scan.getId();
    }

    public Map<String, Object> toMap() {
        return scan.toMap();
    }

    public String toJSON(int maxCols) throws IOException {
        return scan.toJSON(maxCols);
    }

    public String toJSON() throws IOException {
        return scan.toJSON();
    }

    public String toString(int maxCols) {
        return scan.toString(maxCols);
    }
}