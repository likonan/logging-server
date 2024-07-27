package com.swpu.hotelserver.emp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.swpu.hotelserver.emp.dto.RoomCheckPageDTO;
import com.swpu.hotelserver.emp.entity.Room;
import com.swpu.hotelserver.emp.mapper.RoomMapper;
import com.swpu.hotelserver.emp.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huchengbo
 * @since 2024-07-24
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    /**
     * 查询所有房间信息
     * @return 房间信息列表
     */
    @Override
    public List<Room> listAllRooms() {
        return super.list();
    }

    /**
     * 根据ID查询房间信息
     * @param id 房间ID
     * @return 房间信息
     */
    @Override
    public Room getRoomById(Integer id) {
        return super.getById(id);
    }

    /**
     * 添加房间信息
     * @param room 房间实体对象
     * @return 操作是否成功
     */
    @Override
    public boolean addRoom(Room room) {
        return super.save(room);
    }

    /**
     * 修改房间信息
     * @param room 房间实体对象
     * @return 操作是否成功
     */
    @Override
    public boolean updateRoom(Room room) {
        return super.updateById(room);
    }

    /**
     * 删除房间信息
     * @param id 房间ID
     * @return 操作是否成功
     */
    @Override
    public boolean deleteRoom(Integer id) {
        return super.removeById(id);
    }

    @Override
    public Page<Room> getRoomCheckPage(RoomCheckPageDTO roomCheckPageDTO) {
        Page<Room> page = new Page<>(roomCheckPageDTO.getPageNumber(), roomCheckPageDTO.getPageSize());
            QueryWrapper<Room> w = new QueryWrapper<>();
            w.like(roomCheckPageDTO.getRoomNum()!=null, "room_num", roomCheckPageDTO.getRoomNum())
                    .like(roomCheckPageDTO.getType()!=null, "type", roomCheckPageDTO.getType())
                    .like(roomCheckPageDTO.getStatus()!=null, "status", roomCheckPageDTO.getStatus())
                    .orderByAsc("id");
            this.page(page, w);
            return page;
    }
}
