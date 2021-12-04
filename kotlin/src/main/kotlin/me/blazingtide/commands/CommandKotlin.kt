package me.blazingtide.commands

import me.blazingtide.commands.command.Command

inline fun command(builder: KCommandBuilder.() -> Unit): Command {
    val conf = KCommandBuilder().apply(builder)

    val builder = Commands.begin()
        .label(conf._label)

    if (conf._permission != null) {
        builder.permission(conf._permission)
    }

    if (conf._usage != null) {
        builder.usage(conf._usage)
    }

    if (conf._async) {
        builder.async()
    }

    builder.execute { conf._executor(it) }

    return builder.create()
}
