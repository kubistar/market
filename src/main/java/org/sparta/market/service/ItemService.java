package org.sparta.market.service;

import org.sparta.market.dto.ItemDTO;
import org.sparta.market.dto.ItemRequestDTO;
import org.sparta.market.entity.Item;
import org.sparta.market.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = new Item();
        BeanUtils.copyProperties(itemDTO, item);
        Item savedItem = itemRepository.save(item);
        return convertToDTO(savedItem);
    }

    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ItemDTO updateItem(Long id, ItemRequestDTO requestDTO) {
        // 해당 아이템이 DB에 존재하는지 확인
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        // 요청 DTO의 내용으로 기존 아이템 엔티티를 업데이트
        existingItem.setTitle(requestDTO.getTitle());
        existingItem.setContent(requestDTO.getContent());
        existingItem.setPrice(requestDTO.getPrice());
        existingItem.setUsername(requestDTO.getUsername());

        // 업데이트된 아이템을 저장하고, 저장된 아이템을 DTO로 변환하여 반환
        Item updatedItem = itemRepository.save(existingItem);
        return convertToDTO(updatedItem);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDTO convertToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        BeanUtils.copyProperties(item, itemDTO);
        return itemDTO;
    }
}
