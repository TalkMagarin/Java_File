package com.magarin.economy_bank;

import com.magarin.economy_bank.event.Event_Handler;
import com.magarin.economy_bank.sqlite.Sqlite_Installment_Savings;
import com.magarin.economy_bank.yaml.Yaml_Setting;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy_Bank extends JavaPlugin {

    @Override
    public void onEnable() {
        __on_Resource_file__();

        __on_Sqlite_Class_Instance__();
        __on_Sqlite_Table__();

        __on_Is_Support_Plugin__();

        // 이벤트 기능 활성화
        PluginManager __Plugin_Manager__ = this.getServer().getPluginManager();
        __Plugin_Manager__.registerEvents(new Event_Handler(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
<pre>
플러그인을 서포터랑 라이브러리 플러그인이 존재하는지 확인합니다.
존재하지 않을 경우 플러그인이 서버 리부트 전까지 중지합니다.
</pre>
     */
    private void __on_Is_Support_Plugin__() {
        if (getServer().getPluginManager().getPlugin("Vault") != null) {
            System.out.println("[" + this.getName() + "] Vault 플러그인 존재를 확인하였습니다.");

            try { Thread.sleep(500); } catch (Exception __Exception__) {}

            if (getServer().getPluginManager().getPlugin("Essentials") != null)
                System.out.println("[" + this.getName() + "] Essentials 플러그인 존재를 확인하였습니다.");
            else {
                System.out.println("[" + this.getName() + "] Essentials 플러그인이 존재하지 않습니다.");
                System.out.println("[" + this.getName() + "] 안전을 위해 플러그인을 중지합니다.");

                this.getServer().getPluginManager().disablePlugin(this);
                return;
            }

            try { Thread.sleep(500); } catch (Exception __Exception__) {}
            System.out.println("[" + this.getName() + "] Vault 플러그인 인식 여부를 확인합니다.");
            try { Thread.sleep(1000); } catch (Exception __Exception__) {}

            if (Economy_Handler.__Eco_Handler__Setup_Economy__()) {
                System.out.println("[" + this.getName() + "] Vault 플러그인이 정상적으로 인식되었습니다.");
            } else {
                System.out.println("[" + this.getName() + "] Vault 플러그인이 인식이 되지 않았습니다.");
                System.out.println("[" + this.getName() + "] 안전을 위해 플러그인을 중지합니다.");

                this.getServer().getPluginManager().disablePlugin(this);
                return;
            }
        } else {
            System.out.println("[" + this.getName() + "] Vault 플러그인이 존재하지 않습니다.");
            System.out.println("[" + this.getName() + "] 안전을 위해 플러그인을 중지합니다.");

            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    /**
     * 커스텀으로 제작된 Yaml 파일을 생성합니다.
     */
    private void __on_Resource_file__() {
        saveResource("__Bank_Setting__.yml", false);
    }

    private Sqlite_Installment_Savings __Sqlite_Installment_Savings__ = null;

    private Yaml_Setting __Yaml_Setting__ = null;

    /**
     * 데이터베이스를 관리하는 클래스를 Instance 로 생성합니다.
     */
    private void __on_Sqlite_Class_Instance__() {
        __Sqlite_Installment_Savings__ = Sqlite_Installment_Savings.__Instance__();

        __Yaml_Setting__ = Yaml_Setting.__Instance__();
    }

    /**
     * 데이터베이스에 각 테이블을 생성합니다.
     */
    private void __on_Sqlite_Table__() {
        __Sqlite_Installment_Savings__.__Sqlite__Create_Table__();
    }

}
