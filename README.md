# SimpleCommandTimer

[![download](https://img.shields.io/github/downloads/VertrauterDavid/SimpleCommandTimer/total?style=for-the-badge)](https://github.com/VertrauterDavid/SimpleCommandTimer/releases/latest)
![license](https://img.shields.io/github/license/VertrauterDavid/SimpleCommandTimer?style=for-the-badge)
![stars](https://img.shields.io/github/stars/VertrauterDavid/SimpleCommandTimer?style=for-the-badge)
![forks](https://img.shields.io/github/forks/VertrauterDavid/SimpleCommandTimer?style=for-the-badge)

<hr>

### Installation
1. Download jar from [here](https://github.com/VertrauterDavid/SimpleCommandTimer/releases/latest)
2. Put the jar in your plugins folder
3. Restart your server (not reload)

### Update
1. Download the new jar from [here](https://github.com/VertrauterDavid/SimpleCommandTimer/releases/latest)
2. Replace the old jar with the new one
3. Delete the old `config.yml` and restart your server

<hr>

Config template for Keyall command every hour:
```yaml
Timers:
  keyall: '3600;crate key giveall keyall 1'
```

<hr>

<details>
    <summary><h3 style="display: inline;">Placeholders</h3></summary>

| Placeholder          | Description                                | Example |
|:---------------------|:-------------------------------------------|:--------|
| `%timer_identifier%` | 'identifier' must be defined in the config |         |
| `%timer_keyall%`     | Example for the keyall template            | 48m 25s |

</details>
