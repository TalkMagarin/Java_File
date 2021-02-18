package magarin.prefixplugin;
/**
 * 1. https://gist.github.com/aadnk/0502e32369f203daaba9
 * https://stackoverflow.com/questions/38407593/bukkit-change-players-name-above-head
 * https://www.spigotmc.org/threads/protocollib-disguise-player-with-new-name.301599/
 * https://gist.github.com/Techcable/d574a6e78ada3b8511bd
 */
// For ProtocolLib
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedGameProfile;

// For Bukkit
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class PrefixPlugin_Display {

    private static final String __PROFILE_URL__ = "https://sessionserver.mojang.com/session/minecraft/profile/";
    private static final int __WORKER_THREADS__ = 4;

    private ProtocolManager __Protocol_Manager__;
    private ConcurrentMap<String, String> __Map__Skin_Names__ = Maps.newConcurrentMap();
    private ConcurrentMap<String, String> __Map__Display_Names__ = Maps.newConcurrentMap();

    private Cache<String, String> __Profile_Cache__ = CacheBuilder.newBuilder().maximumSize(500)
            .expireAfterWrite(4, TimeUnit.HOURS).build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return get;
                }
            })

    public PrefixPlugin_Display(Plugin __in_Plugin__) {
        __Protocol_Manager__ = ProtocolLibrary.getProtocolManager();
        __Protocol_Manager__.getAsynchronousManager().registerAsyncHandler(
                new PacketAdapter(__in_Plugin__, ListenerPriority.NORMAL,
                        PacketType.Play.Server.NAMED_ENTITY_SPAWN,
                        PacketType.Play.Server.ENTITY_EFFECT,
                        PacketType.Play.Server.ENTITY_EQUIPMENT,
                        PacketType.Play.Server.ENTITY_METADATA,
                        PacketType.Play.Server.UPDATE_ATTRIBUTES,
                        PacketType.Play.Server.ATTACH_ENTITY,
                        PacketType.Play.Server.BED)
                {
                    @Override
                    public void onPacketSending(PacketEvent __in_Event__) {
                        if (__in_Event__.getPacketType() != PacketType.Play.Server.NAMED_ENTITY_SPAWN)
                            return;

                        Player __Player__ = (Player) __in_Event__.getPacket().getEntityModifier(__in_Event__).read(0);
                        String __Skin_Player_name__ = __Map__Skin_Names__.get(__Player__.getName());
                        String __Display_Player_name__ = __Map__Display_Names__.get(__Player__.getName());

                        if (__Skin_Player_name__ == null && __Display_Player_name__ == null)
                            return;

                        StructureModifier<WrappedGameProfile> __Profiles__ = __in_Event__.getPacket().getGameProfiles();
                        WrappedGameProfile __Original__ = __Profiles__.read(0);
                        WrappedGameProfile __Result__ = new WrappedGameProfile(
                                __Display__Extract_UUID__(__Original__.getName()),
                                __Display_Player_name__ != null ? __Display_Player_name__ : __Original__.getName()
                        );

                        __Display__Update_Skin__(__Result__, __Skin_Player_name__ != null ? __Skin_Player_name__ : __Result__.getName());
                        __Profiles__.write(0, __Result__);
                    }
                }
        ).start(__WORKER_THREADS__);
    }

    private UUID __Display__Extract_UUID__(final String __in_Player_name__) {
        return Bukkit.getOfflinePlayer(__in_Player_name__).getUniqueId();
    }

    private String __Display__Get_Profile_Json(String __in_Name__) throws IOException {
        final URL __Url__ = new URL(__PROFILE_URL__ + __Display__Extract_UUID__(__in_Name__).toString().replace("-", ""));
        final URLConnection __Url_Connection__ = __Url__.openConnection();

        return CharStreams.toString(new InputSupp)
    }

    private void __Display__Update_Skin__(WrappedGameProfile __in_Profile__, String __in_Skin_Player_name__) {
        try {
            JsonObject __Json__ = (JsonObject) new JsonParser().parse(Pro);
        } catch (Exception __Exception__) {
            throw  new RuntimeException("Cannot fetch profile for " + __in_Skin_Player_name__, __Exception__);
        }
    }

    public void __Display__Change_Player_name__(String __in_Player_name__, String __in_Skin_Player_name__, String __in_Change_Player_name__) {
        if (__Display__Update_Map__(__Map__Skin_Names__, __in_Player_name__, __in_Skin_Player_name__) |
                __Display__Update_Map__(__Map__Display_Names__, __in_Player_name__, __in_Change_Player_name__)) {
            __Display__Refresh_Player_name__(__in_Player_name__);
        }
    }

    public void __Display__Change_Player_name__(Player __in_Player__, String __in_Skin_Player_name__, String __in_Change_Player_name__) {
        if (__Display__Update_Map__(__Map__Skin_Names__, __in_Player__.getName(), __in_Skin_Player_name__) |
                __Display__Update_Map__(__Map__Display_Names__, __in_Player__.getName(), __in_Change_Player_name__)) {
            __Display__Refresh_Player_name__(__in_Player__);
        }
    }

    private <T, U> boolean __Display__Update_Map__(Map<T, U> __in_Map__, T __in_Key__, U __in_Value__) {
        if (__in_Value__ == null)
            return __in_Map__.remove(__in_Key__) != null;
        else
            return !Objects.equals(__in_Value__, __in_Map__.put(__in_Key__, __in_Value__));
    }

    private void __Display__Refresh_Player_name__(String __in_Player_name__) {
        Player __Player__ = Bukkit.getPlayer(__in_Player_name__);

        if (__Player__ != null)
            __Display__Refresh_Player_name__(__Player__);
    }

    private void __Display__Refresh_Player_name__(Player __in_Player__) {
        __Protocol_Manager__.updateEntity(__in_Player__, __Protocol_Manager__.getEntityTrackers(__in_Player__));
    }

}
