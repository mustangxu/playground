job("Qodana") {
    // For example: "jetbrains/qodana-jvm-community:2021.3"
    container("jetbrains/qodana-jvm:2022.3-eap") {
        // https://www.jetbrains.com/help/space/secrets-and-parameters.html
        env["QODANA_TOKEN"] = Secrets("qodana-token")
        shellScript {
            content = """
               QODANA_REPO_URL="${'$'}JB_SPACE_API_URL/p/${'$'}JB_SPACE_PROJECT_KEY/repositories/${'$'}JB_SPACE_GIT_REPOSITORY_NAME" \
               QODANA_BRANCH=${'$'}JB_SPACE_GIT_BRANCH \
               QODANA_REVISION=${'$'}JB_SPACE_GIT_REVISION \
               QODANA_ENV="space" \
               qodana
            """.trimIndent()
        }
    }
}