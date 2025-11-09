(ns deadlib.deadlock-api.heroes
  (:require [deadlib.deadlock-api.util :refer [unmarshal-map]]))


(defrecord HeroDescription [lore role playstyle])

(defn- parse-HeroDescription [as-json]
  (->HeroDescription
   (:lore as-json)
   (:role as-json)
   (:playstyle as-json)))

(defrecord HeroImages [hero-card
                       hero-card-webp
                       small
                       small-webp
                       minimap
                       minimap-webp
                       selection-image
                       selection-image-webp
                       top-bar
                       top-bar-webp
                       top-bar-vertical
                       top-bar-vertical-webp
                       weapon
                       weapon-webp
                       background
                       background-webp
                       name-image])

(defn- parse-HeroImages [as-json]
  (->HeroImages
   (:icon_hero_card as-json)
   (:icon_hero_card_webp as-json)
   (:icon_image_small as-json)
   (:icon_image_small_webp as-json)
   (:minimap_image as-json)
   (:minimap_image_webp as-json)
   (:selection_image as-json)
   (:selection_image_webp as-json)
   (:top_bar_image as-json)
   (:top_bar_image_webp as-json)
   (:top_bar_vertical_image as-json)
   (:top_bar_vertical_image_webp as-json)
   (:weapon_image as-json)
   (:weapon_image_webp as-json)
   (:background_image as-json)
   (:background_image_webp as-json)
   (:name_image as-json)))

(defrecord StartingStat [display-name value])

(defn- parse-StartingStat [as-json]
  (->StartingStat
   (:display_stat_name as-json)
   (:value as-json)))

(defrecord StartingStats [max-move-speed
                          sprint-speed
                          crouch-speed
                          move-acceleration
                          light-melee-damage
                          heavy-melee-damage
                          max-health
                          weapon-power
                          reload-speed
                          weapon-power-scale
                          proc-build-up-rate-scale
                          stamina
                          base-health-regen
                          stamina-regen-per-second
                          ability-resource-max
                          ability-resource-regen-per-second
                          crit-adamage-received-scale
                          tech-duration
                          tech-armor-damage-reduction
                          tech-range
                          bullet-armor-damage-reduction])

(defrecord HeroPhysics [collision-height
                        collision-radius
                        stealth-speed-meters-per-second
                        step-height
                        footstep-sound-travel-distance-meters
                        step-sound-time
                        step-sound-time-sprinting])

(defrecord HeroColors [glow-enemy glow-friendly glow-team1 glow-team2 ui])

(defrecord ShopSpiritStatsDisplay [display-stats])

(defrecord ShoptVitalityStatsDisplay [display-stats other-display-stats])

(defrecord ShopWeaponStatsDisplay [display-stats other-display-stats weapon-attributes weapon-image weapon-image-webp])

(defrecord ShopStatsDisplay [spirit-stats vitality-stats weapon-stats])

(defrecord CostBonus [gold-threshold bonus percent-on-graph])

(defrecord CostBonuses [bonuses])

(defrecord StatsDisplay [health-header-stats
                         health-body-stats
                         magic-header-stats
                         magic-stats
                         weapon-header-stats
                         weapon-stats])

(defrecord DisplayStats [category stat-type])

(defrecord HeroStatsUI [weapon-stat-display display-stats])

(defrecord Hero [id
                 class-name
                 name
                 recommended-upgrades
                 recommended-ability-order
                 player-selectable?
                 disabled?
                 in-development?
                 needs-testing?
                 assigned-players-only?
                 tags
                 gun-tag
                 hideout-rich-presence
                 hero-type
                 prerelease-only?
                 limited-testing?
                 complexity
                 skin
                 images
                 items
                 starting-stats
                 item-slot-info
                 physics
                 colors
                 shop-stats-display
                 cost-bonuses
                 stats-display
                 hero-stats-ui])

(defn hero-from-map [as-json]
  (map->Hero (unmarshal-map as-json {:id {:unmarshal-fn (fn [x] {:id x})}
                                     :class_name {:unmarshal-fn (fn [x] {:class-name x})}
                                     :name {:unmarshal-fn (fn [x] {:name x})}
                                     :description {:unmarshal-fn (fn [x] {:description x})}
                                     :purchase_bonuses {:unmarshal-fn (fn [x] {:purchase-bonuses x})}
                                     :scaling_stats {:unmarshal-fn (fn [x] {:scaling-stats x})}
                                     :level_info {:unmarshal-fn (fn [x] {:level-info x})}
                                     :standard_level_up_upgrades {:unmarshal-fn (fn [x] {:standard-level-up-upgrades x})}
                                     :recommended_upgrades {:unmarshal-fn (fn [x] {:recommended-upgrades x})}
                                     :recommended_ability_order {:unmarshal-fn (fn [x] {:recommended-ability-order x})}
                                     :player_selectable {:unmarshal-fn (fn [x] {:player-selectable? x})}
                                     :disabled {:unmarshal-fn (fn [x] {:disabled? x})}
                                     :in_development {:unmarshal-fn (fn [x] {:in-development? x})}
                                     :needs_testing {:unmarshal-fn (fn [x] {:needs-testing? x})}
                                     :assigned_players_only {:unmarshal-fn (fn [x] {:assigned-players-only? x})}
                                     :tags {:unmarshal-fn (fn [x] {:tags x})}
                                     :gun_tag {:unmarshal-fn (fn [x] {:gun-tag x})}
                                     :hideout_rich_presence {:unmarshal-fn (fn [x] {:hideout-rich-presence x})}
                                     :hero_type {:unmarshal-fn (fn [x] {:hero-type x})}
                                     :prerelease_only {:unmarshal-fn (fn [x] {:prerelease-only? x})}
                                     :limited_testing {:unmarshal-fn (fn [x] {:limited-testing? x})}
                                     :complexity {:unmarshal-fn (fn [x] {:complexity x})}
                                     :skin {:unmarshal-fn (fn [x] {:skin x})}
                                     :images {:unmarshal-fn (fn [x] {:images x})}
                                     :items {:unmarshal-fn (fn [x] {:items x})}
                                     :starting_stats {:unmarshal-fn (fn [x] {:starting-stats x})}
                                     :item_slot_info {:unmarshal-fn (fn [x] {:item-slot-info x})}
                                     :physics {:unmarshal-fn (fn [x] {:physics x})}
                                     :colors {:unmarshal-fn (fn [x] {:colors x})}
                                     :shop_stat_display {:unmarshal-fn (fn [x] {:shop-stats-display x})}
                                     :cost_bonuses {:unmarshal-fn (fn [x] {:cost-bonuses x})}
                                     :stats_display {:unmarshal-fn (fn [x] {:stats-display x})}
                                     :hero_stats_ui {:unmarshal-fn (fn [x] {:hero-stats-ui x})}})))
