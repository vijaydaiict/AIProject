rootProject.name = "nwaysetcache1"

include(
    "service",
    "platform:cache:core",
    "platform:cache:api",
    "platform:cache:adapters:redis",
    "platform:cache:adapters:memory"
)
