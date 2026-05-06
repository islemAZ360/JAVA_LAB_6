# Graph Report - src  (2026-05-05)

## Corpus Check
- 45 files · ~9,196 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 301 nodes · 560 edges · 16 communities detected
- Extraction: 64% EXTRACTED · 36% INFERRED · 0% AMBIGUOUS · INFERRED: 202 edges (avg confidence: 0.8)
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
- [[_COMMUNITY_Community 15|Community 15]]

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
- `Coordinates` --implements--> `Serializable`  [EXTRACTED]
  common\models\Coordinates.java →   _Bridges community 5 → community 3_
- `HumanBeing` --implements--> `Serializable`  [EXTRACTED]
  common\models\HumanBeing.java →   _Bridges community 5 → community 1_
- `AddCommand` --implements--> `Command`  [EXTRACTED]
  server\commands\AddCommand.java →   _Bridges community 1 → community 0_
- `AddIfMaxCommand` --implements--> `Command`  [EXTRACTED]
  server\commands\AddIfMaxCommand.java →   _Bridges community 0 → community 2_
- `FilterGreaterThanCarCommand` --implements--> `Command`  [EXTRACTED]
  server\commands\FilterGreaterThanCarCommand.java →   _Bridges community 0 → community 10_

## Communities

### Community 0 - "Community 0"
Cohesion: 0.05
Nodes (11): ClearCommand, Command, ExecuteScriptCommand, FilterContainsNameCommand, FilterLessThanMinutesOfWaitingCommand, InfoCommand, RemoveByIdCommand, RemoveGreaterCommand (+3 more)

### Community 1 - "Community 1"
Cohesion: 0.13
Nodes (2): AddCommand, HumanBeing

### Community 2 - "Community 2"
Cohesion: 0.1
Nodes (3): AddIfMaxCommand, AddIfMinCommand, HumanBeingReader

### Community 3 - "Community 3"
Cohesion: 0.13
Nodes (3): Coordinates, HumanBeingBuilder, HumanBeingChecker

### Community 4 - "Community 4"
Cohesion: 0.12
Nodes (1): CollectionManager

### Community 5 - "Community 5"
Cohesion: 0.09
Nodes (4): Car, Request, Response, Serializable

### Community 6 - "Community 6"
Cohesion: 0.09
Nodes (4): CommandManager, CommandSuggester, HelpCommand, RequestHandler

### Community 7 - "Community 7"
Cohesion: 0.13
Nodes (4): BooleanBuilder, ClientMain, InputManager, LongBuilder

### Community 8 - "Community 8"
Cohesion: 0.18
Nodes (4): AutoCloseable, RequestSender, Serializer, ServerMain

### Community 9 - "Community 9"
Cohesion: 0.14
Nodes (6): CommandFileManager, FileManager, FileManager, HandleCommandFile, HandleHumanBeingFile, HumanBeingFileManager

### Community 10 - "Community 10"
Cohesion: 0.29
Nodes (1): FilterGreaterThanCarCommand

### Community 11 - "Community 11"
Cohesion: 0.33
Nodes (1): HandleHumanBeingFile

### Community 12 - "Community 12"
Cohesion: 0.4
Nodes (1): Command

### Community 13 - "Community 13"
Cohesion: 0.67
Nodes (1): HandleCommandFile

### Community 14 - "Community 14"
Cohesion: 1.0
Nodes (1): Const

### Community 15 - "Community 15"
Cohesion: 1.0
Nodes (1): ConnectionState

## Knowledge Gaps
- **2 isolated node(s):** `Const`, `ConnectionState`
  These have ≤1 connection - possible missing edges or undocumented components.
- **Thin community `Community 1`** (37 nodes): `AddCommand`, `.AddCommand()`, `.execute()`, `.getDescription()`, `.getName()`, `.update()`, `.getX()`, `.getY()`, `HumanBeing`, `.getCar()`, `.getCoordinates()`, `.getCreationDate()`, `.getImpactSpeed()`, `.getMinutesOfWaiting()`, `.getName()`, `.getSoundtrackName()`, `.getWeaponType()`, `.HumanBeing()`, `.isHasToothpick()`, `.isRealHero()`, `.setCar()`, `.setCoordinates()`, `.setHasToothpick()`, `.setId()`, `.setImpactSpeed()`, `.setMinutesOfWaiting()`, `.setName()`, `.setRealHero()`, `.setSoundtrackName()`, `.setValueCreationDate()`, `.setWeaponType()`, `.toString()`, `.extractInfo()`, `.getObjectArgument()`, `AddCommand.java`, `.execute()`, `.getName()`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 4`** (28 nodes): `.execute()`, `CollectionManager`, `.add()`, `.addAll()`, `.clear()`, `.CollectionManager()`, `.contains()`, `.getCollectionInfo()`, `.getHumanBy()`, `.getHumanById()`, `.getHumanByName()`, `.remove()`, `.removeAll()`, `.removeById()`, `.removeGreater()`, `.show()`, `.size()`, `.toString()`, `.execute()`, `.getName()`, `.execute()`, `.saveAll()`, `.execute()`, `.execute()`, `.execute()`, `.getStringArgument()`, `.execute()`, `CollectionManager.java`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 10`** (7 nodes): `.isCool()`, `FilterGreaterThanCarCommand`, `.execute()`, `.FilterGreaterThanCarCommand()`, `.getDescription()`, `.getName()`, `FilterGreaterThanCarCommand.java`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 11`** (6 nodes): `HandleHumanBeingFile.java`, `HandleHumanBeingFile`, `.readFileAndLoadHumanBeing()`, `.save()`, `.saveAll()`, `.saveOne()`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 12`** (5 nodes): `Command`, `.execute()`, `.getDescription()`, `.getName()`, `Command.java`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 13`** (3 nodes): `HandleCommandFile.java`, `HandleCommandFile`, `.readFileAndRunScripts()`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 14`** (2 nodes): `Const.java`, `Const`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 15`** (2 nodes): `ConnectionState`, `ConnectionState.java`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `HumanBeing` connect `Community 1` to `Community 9`, `Community 2`, `Community 5`, `Community 6`?**
  _High betweenness centrality (0.091) - this node is a cross-community bridge._
- **Why does `CollectionManager` connect `Community 4` to `Community 1`, `Community 2`, `Community 9`?**
  _High betweenness centrality (0.085) - this node is a cross-community bridge._
- **Why does `Response` connect `Community 5` to `Community 3`?**
  _High betweenness centrality (0.047) - this node is a cross-community bridge._
- **What connects `Const`, `ConnectionState` to the rest of the system?**
  _2 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `Community 0` be split into smaller, more focused modules?**
  _Cohesion score 0.05 - nodes in this community are weakly interconnected._
- **Should `Community 1` be split into smaller, more focused modules?**
  _Cohesion score 0.13 - nodes in this community are weakly interconnected._
- **Should `Community 2` be split into smaller, more focused modules?**
  _Cohesion score 0.1 - nodes in this community are weakly interconnected._