# SimpleCommandTimer


### Config template for Keyall command every hour
```yaml
Timers:
  keyall: '3600;crate key giveall keyall 1'
```


### Placeholders
| Placeholder          | Description                                | Example |
|:---------------------|:-------------------------------------------|:--------|
| `%timer_identifier%` | 'identifier' must be defined in the config |         |
| `%timer_keyall%`     | Example for the keyall template            | 48m 25s |
