package com.dsaproblems.DSAProblems.advancedJava;

import lombok.Getter;

import java.util.*;

@Getter
class Pair<K, V> {
    private K key;
    private V val;

    Pair(K key, V val) {
        this.key = key;
        this.val = val;
    }
}

public class ConsistentHashing {

    @Getter
    private final ArrayList<Integer> answers;

    private final Map<Integer, String> locationToServerMapping;

    private final Map<String, List<Pair<String, Integer>>> serverToKeyMappings;

    public ConsistentHashing() {
        this.answers = new ArrayList<>();
        this.locationToServerMapping = new TreeMap<>();
        this.serverToKeyMappings = new HashMap<>();
    }

    public void performOperation(String a, String b, int c) {
        switch (a) {
            case "ADD" -> {
                answers.add(addServer(b, c));
            }
            case "REMOVE" -> {
                answers.add(removeServer(b, c));
            }
            case "ASSIGN" -> {
                answers.add(assignRequest(b, c));
            }
        }
    }

    private int assignRequest(String keyName, int hashKey) {
        if (this.locationToServerMapping.isEmpty()) return -1;
        int keyLocation = userHash(keyName, hashKey);
        Map.Entry<Integer, String> it = locationToServerMapping
                .entrySet()
                .stream()
                .filter(e -> e.getKey() >= keyLocation)
                .findFirst()
                .orElse(locationToServerMapping.entrySet().stream().findFirst().get());
        String serverId = it.getValue();
        serverToKeyMappings.computeIfAbsent(serverId, k -> new ArrayList<>()).add(new Pair<>(keyName, hashKey));
        return it.getKey();
    }

    private Integer removeServer(String serverId, int hashKey) {
        for (Iterator<Map.Entry<Integer, String>> it = this.locationToServerMapping
                .entrySet()
                .iterator(); it.hasNext(); ) {
            Map.Entry<Integer, String> entry = it.next();
            if (entry.getValue().equals(serverId)) {
                it.remove();
                break;
            }
        }
        List<Pair<String, Integer>> keynamesToReassign = this.serverToKeyMappings.getOrDefault(serverId, Collections.emptyList());
        this.serverToKeyMappings.remove(serverId);
        for (Pair<String, Integer> keyname : keynamesToReassign) {
            assignRequest(keyname.getKey(), keyname.getVal());
        }
        return keynamesToReassign.size();
    }

    private Integer addServer(String serverId, int hashKey) {
        int firstLocation = userHash(serverId, hashKey);
        this.locationToServerMapping.put(firstLocation, serverId);
        findRequestsToServe(firstLocation, hashKey);
        return serverToKeyMappings.getOrDefault(serverId, new ArrayList<>()).size();
    }

    private void findRequestsToServe(int serverLocation, int hashKey) {
        if (this.serverToKeyMappings.isEmpty()) return;
        Map.Entry<Integer, String> it = locationToServerMapping.entrySet().stream()
                .filter(e -> e.getKey() > serverLocation)
                .findFirst()
                .orElse(locationToServerMapping.entrySet().stream().findFirst().get());
        String serverId = it.getValue();
        List<Pair<String, Integer>> keyNames = serverToKeyMappings.getOrDefault(serverId, new ArrayList<>());
        serverToKeyMappings.remove(serverId);
        for (Pair<String, Integer> entry : keyNames) {
            assignRequest(entry.getKey(), entry.getVal());
        }
    }

    private int userHash(String userName, int hashKey) {
        final int n = 360;
        long hashCode = 0L;
        long p_pow = 1;
        for (char c : userName.toCharArray()) {
            hashCode = (hashCode + (c - 'A' + 1) * p_pow) % n;
            p_pow = (p_pow * hashKey) % n;
        }
        return (int) hashCode;
    }
}
