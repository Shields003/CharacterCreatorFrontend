import clients.Clients
import components.CharacterClassLevelComponent
import components.CharacterClassLevelProps
import kotlinx.coroutines.CoroutineScope
import player.PlayerCharacter
import player.PlayerService
import react.*
import kotlin.random.Random
import kotlin.reflect.KClass

external interface AppState: RState, AppResources {
    var playerService: PlayerService
    var playerCharacters: List<PlayerCharacter>
}

external interface AppResources {
    var clients: Clients
    var coroutineScope: CoroutineScope
    var random: Random


}
fun <T, C> RBuilder.appComponent(
        klass: KClass<C>,
        parent: AppResources,
        handler: T.() -> Unit
): ReactElement
        where T: RProps, T: AppResources, C: Component<T, *> {
    return child(klass) {
        attrs {
            copyFrom(parent)
            handler()
        }
    }
}



fun AppResources.copyFrom(other: AppResources) {
    clients = other.clients
    coroutineScope = other.coroutineScope
    random = other.random
}

fun AppResources.generateElementId(): String {
    return (1..10).map {
        random.nextInt(100)
    }.joinToString("-")
}
