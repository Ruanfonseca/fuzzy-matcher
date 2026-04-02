package fuzzy


import java.text.Normalizer

class FuzzyMatcher {

    /**
     * Retorna similaridade entre duas strings em porcentagem (0 a 100)
     */
    static int similarityPercent(String s1, String s2) {
        String str1 = normalize(s1)
        String str2 = normalize(s2)

        int maxLen = Math.max(str1.length(), str2.length())
        if (maxLen == 0) return 100

        int distance = levenshtein(str1, str2)

        double similarity = (1.0 - (distance / maxLen)) * 100

        return Math.round(similarity)
    }

    /**
     * Distância de Levenshtein
     */
    private static int levenshtein(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1]

        for (int i = 0; i <= s1.length(); i++) dp[i][0] = i
        for (int j = 0; j <= s2.length(); j++) dp[0][j] = j

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = s1[i - 1] == s2[j - 1] ? 0 : 1

                dp[i][j] = Math.min(
                        Math.min(
                                dp[i - 1][j] + 1,
                                dp[i][j - 1] + 1
                        ),
                        dp[i - 1][j - 1] + cost
                )
            }
        }

        return dp[s1.length()][s2.length()]
    }

    private static String normalize(String str) {
        if (!str) return ""

        // Decompõe acentos (ex: á -> a +  ́)
        String normalized = java.text.Normalizer.normalize(str, java.text.Normalizer.Form.NFD)

        // Mantém apenas caracteres ASCII
        StringBuilder asciiOnly = new StringBuilder()

        for (char c : normalized.toCharArray()) {
            if (c <= 127) { // ASCII range
                asciiOnly.append(c)
            }
        }

        return asciiOnly
                .toString()
                .toLowerCase()
                .trim()
    }
}