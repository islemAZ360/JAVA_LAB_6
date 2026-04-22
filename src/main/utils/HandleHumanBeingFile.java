package main.utils;

import common.models.HumanBeing;

import java.util.Collection;

public interface HandleHumanBeingFile {
    void readFileAndLoadHumanBeing(CollectionManager collectionManager);
    void save(HumanBeing human);
    void saveOne(HumanBeing human);
    void saveAll(Collection<HumanBeing> collection);
}
