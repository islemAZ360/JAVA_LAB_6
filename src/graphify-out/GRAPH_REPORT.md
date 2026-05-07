# Graph Report - src  (2026-05-06)

## Corpus Check
- 48 files · ~10,337 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 312 nodes · 586 edges · 15 communities detected
- Extraction: 62% EXTRACTED · 38% INFERRED · 0% AMBIGUOUS · INFERRED: 220 edges (avg confidence: 0.8)
- Token cost: 0 input · 0 output

## Community Hubs (Navigation)
- [[_COMMUNITY_Community 0|Community 0]]
- [[_COMMUNITY_Community 1|Community 1]]
- [[_COMMUNITY_Community 2|Community 2]]
- [[_COMMUNITY_Community 3|Community 3]]
- [[_COMMUNITY_Community 4|Community 4]]
- [[_COMMUNITY_Community 5|Community 5]]
- [[_COMMUNITY_Community 6|Community 6]]
- [[_COMMUNITY_Community 7|Community 7]]
- [[_COMMUNITY_Community 8|Community 8]]
- [[_COMMUNITY_Community 9|Community 9]]
- [[_COMMUNITY_Community 10|Community 10]]
- [[_COMMUNITY_Community 11|Community 11]]
- [[_COMMUNITY_Community 12|Community 12]]
- [[_COMMUNITY_Community 13|Community 13]]
- [[_COMMUNITY_Community 14|Community 14]]

## God Nodes (most connected - your core abstractions)
1. `HumanBeing` - 31 edges
2. `CollectionManager` - 24 edges
3. `HumanBeingBuilder` - 13 edges
4. `HumanBeingChecker` - 10 edges
5. `HumanBeingFileManager` - 8 edges
6. `Request` - 7 edges
7. `Response` - 7 edges
8. `Coordinates` - 7 edges
9. `CommandManager` - 7 edges
10. `AddIfMaxCommand` - 7 edges

## Surprising Connections (you probably didn't know these)
- `Request` --implements--> `Serializable`  [EXTRACTED]
  common\Request.java →   _Bridges community 8 → community 6_
- `HumanBeing` --implements--> `Serializable`  [EXTRACTED]
  common\models\HumanBeing.java →   _Bridges community 6 → community 3_
- `AddCommand` --implements--> `Command`  [EXTRACTED]
  server\commands\AddCommand.java →   _Bridges community 3 → community 0_
- `AddIfMaxCommand` --implements--> `Command`  [EXTRACTED]
  server\commands\AddIfMaxCommand.java →   _Bridges community 0 → community 2_
- `ClearCommand` --implements--> `Command`  [EXTRACTED]
  server\commands\ClearCommand.java →   _Bridges community 0 → community 9_

## Communities

### Community 0 - "Community 0"
Cohesion: 0.05
Nodes (10): AddIfMinCommand, Command, ExecuteScriptCommand, FilterContainsNameCommand, FilterGreaterThanCarCommand, FilterLessThanMinutesOfWaitingCommand, InfoCommand, RemoveGreaterCommand (+2 more)

### Community 1 - "Community 1"
Cohesion: 0.07
Nodes (10): AutoCloseable, BooleanBuilder, ClientMain1, ClientMain2, ClientMain, InputManager, LongBuilder, RequestSender (+2 more)

### Community 2 - "Community 2"
Cohesion: 0.11
Nodes (2): AddIfMaxCommand, CollectionManager

### Community 3 - "Community 3"
Cohesion: 0.15
Nodes (2): AddCommand, HumanBeing

### Community 4 - "Community 4"
Cohesion: 0.08
Nodes (8): CommandFileManager, CommandManager, FileManager, FileManager, HandleCommandFile, HandleHumanBeingFile, HumanBeingFileManager, HumanBeingReader

### Community 5 - "Community 5"
Cohesion: 0.15
Nodes (2): HumanBeingBuilder, HumanBeingChecker

### Community 6 - "Community 6"
Cohesion: 0.1
Nodes (4): Car, Coordinates, Response, Serializable

### Community 7 - "Community 7"
Cohesion: 0.09
Nodes (3): CommandSuggester, HelpCommand, RemoveByIdCommand

### Community 8 - "Community 8"
Cohesion: 0.13
Nodes (3): Request, RequestHandler, UpdateCommand

### Community 9 - "Community 9"
Cohesion: 0.18
Nodes (4): ClearCommand, getLongFlag(), getShortFlag(), matches()

### Community 10 - "Community 10"
Cohesion: 0.33
Nodes (1): HandleHumanBeingFile

### Community 11 - "Community 11"
Cohesion: 0.4
Nodes (1): Command

### Community 12 - "Community 12"
Cohesion: 0.67
Nodes (1): HandleCommandFile

### Community 13 - "Community 13"
Cohesion: 1.0
Nodes (1): Const

### Community 14 - "Community 14"
Cohesion: 1.0
Nodes (1): ConnectionState

## Knowledge Gaps
- **2 isolated node(s):** `Const`, `ConnectionState`
  These have ≤1 connection - possible missing edges or undocumented components.
- **Thin community `Community 2`** (36 nodes): `AddIfMaxCommand`, `.AddIfMaxCommand()`, `.execute()`, `.getDescription()`, `.getName()`, `.isValidId()`, `.execute()`, `.isValidId()`, `.isCool()`, `CollectionManager`, `.add()`, `.addAll()`, `.CollectionManager()`, `.contains()`, `.getHumanBy()`, `.getHumanById()`, `.getHumanByName()`, `.getMax()`, `.getMaxId()`, `.getMin()`, `.isEmpty()`, `.remove()`, `.removeAll()`, `.removeById()`, `.removeGreater()`, `.show()`, `.execute()`, `.getName()`, `.execute()`, `.execute()`, `.compareTo()`, `.getId()`, `.execute()`, `.getStringArgument()`, `CollectionManager.java`, `AddIfMaxCommand.java`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 3`** (32 nodes): `AddCommand`, `.AddCommand()`, `.execute()`, `.getDescription()`, `.getName()`, `.update()`, `HumanBeing`, `.getCar()`, `.getCoordinates()`, `.getCreationDate()`, `.getImpactSpeed()`, `.getMinutesOfWaiting()`, `.getName()`, `.getSoundtrackName()`, `.getWeaponType()`, `.HumanBeing()`, `.isHasToothpick()`, `.isRealHero()`, `.setCar()`, `.setCoordinates()`, `.setHasToothpick()`, `.setId()`, `.setImpactSpeed()`, `.setMinutesOfWaiting()`, `.setName()`, `.setRealHero()`, `.setSoundtrackName()`, `.setValueCreationDate()`, `.setWeaponType()`, `.extractInfo()`, `AddCommand.java`, `.getName()`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 5`** (26 nodes): `HumanBeingBuilder.java`, `HumanBeingChecker.java`, `HumanBeingBuilder`, `.createHumanBeingWithId()`, `.HumanBeingBuilder()`, `.readBoolean()`, `.readCar()`, `.readHumanBeing()`, `.readImpactSpeed()`, `.readMinutesOfWaiting()`, `.readName()`, `.readSoundtrackName()`, `.readWeaponType()`, `.readX()`, `.readY()`, `HumanBeingChecker`, `.checkCar()`, `.checkCoordinates()`, `.checkImpactSpeed()`, `.checkIsHasToothpick()`, `.checkIsRealHero()`, `.checkMinutesOfWaiting()`, `.checkName()`, `.checkSoundtrackName()`, `.checkWeaponType()`, `.getMessage()`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 10`** (6 nodes): `HandleHumanBeingFile.java`, `HandleHumanBeingFile`, `.readFileAndLoadHumanBeing()`, `.save()`, `.saveAll()`, `.saveOne()`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 11`** (5 nodes): `Command`, `.execute()`, `.getDescription()`, `.getName()`, `Command.java`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 12`** (3 nodes): `HandleCommandFile.java`, `HandleCommandFile`, `.readFileAndRunScripts()`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 13`** (2 nodes): `Const.java`, `Const`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 14`** (2 nodes): `ConnectionState`, `ConnectionState.java`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `HumanBeing` connect `Community 3` to `Community 2`, `Community 4`, `Community 6`, `Community 7`, `Community 9`?**
  _High betweenness centrality (0.097) - this node is a cross-community bridge._
- **Why does `CollectionManager` connect `Community 2` to `Community 9`, `Community 3`, `Community 4`, `Community 7`?**
  _High betweenness centrality (0.076) - this node is a cross-community bridge._
- **Why does `Response` connect `Community 6` to `Community 1`, `Community 5`?**
  _High betweenness centrality (0.045) - this node is a cross-community bridge._
- **What connects `Const`, `ConnectionState` to the rest of the system?**
  _2 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Community 0` be split into smaller, more focused modules?**
  _Cohesion score 0.05 - nodes in this community are weakly interconnected._
- **Should `Community 1` be split into smaller, more focused modules?**
  _Cohesion score 0.07 - nodes in this community are weakly interconnected._
- **Should `Community 2` be split into smaller, more focused modules?**
  _Cohesion score 0.11 - nodes in this community are weakly interconnected._