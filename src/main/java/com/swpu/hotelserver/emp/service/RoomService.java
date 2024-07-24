package com.swpu.hotelserver.emp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.swpu.hotelserver.emp.entity.Room;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huchengbo
 * @since 2024-07-24
 */
public interface RoomService extends IService<Room> {
    public List<Room> listAllRooms();
    public Room getRoomById(Integer id);
    public boolean addRoom(Room room);
    public boolean updateRoom(Room room);
    public boolean deleteRoom(Integer id);
}