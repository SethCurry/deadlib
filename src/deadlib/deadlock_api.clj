(ns deadlib.deadlock-api
  (:require [clj-http.client :as http]
            [cheshire.core :as json]))

(defrecord HeroDescription [lore role playstyle])

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
(defrecord StartingStat [display-name value])

(defrecord StartingStats [max-move-speed sprint-speed crouch-speed move-acceleration light-melee-damage heavy-melee-damage max-health weapon-power reload-speed weapon-power-scale proc-build-up-rate-scale stamina base-health-regen stamina-regen-per-second ability-resource-max ability-resource-regen-per-second crit-adamage-received-scale tech-duration tech-armor-damage-reduction tech-range bullet-armor-damage-reduction])

(defrecord HeroPhysics [collision-height collision-radius stealth-speed-meters-per-second step-height footstep-sound-travel-distance-meters step-sound-time step-sound-time-sprinting])

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

(defn list-heroes []
  (let [response (http/get "https://assets.deadlock-api.com/v2/heroes")]
    (json/parse-string (:body response) true)))

(defn list-items []
  (let [response (http/get "https://assets.deadlock-api.com/v2/items")]
    (json/parse-string (:body response) true)))