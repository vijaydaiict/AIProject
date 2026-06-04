dependencies {
    api(project(":platform:cache:core"))
    implementation("redis.clients:jedis:5.1.0")
}

description = "Redis cache adapter implementation"
