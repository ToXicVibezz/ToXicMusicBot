/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ToXicVibezz.ToxicMusic.settings;

import com.ToXicVibezz.ToxicMusic.queue.AbstractQueue;
import com.ToXicVibezz.ToxicMusic.queue.FairQueue;
import com.ToXicVibezz.ToxicMusic.queue.LinearQueue;
import com.ToXicVibezz.ToxicMusic.queue.Queueable;
import com.ToXicVibezz.ToxicMusic.queue.QueueSupplier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Above import dependencies
 * Below is the Queue Type Setting
 */
public enum QueueType
{
    LINEAR("\u23E9", "Linear", LinearQueue::new),     // ⏩
    FAIR("\uD83D\uDD22", "Fair", FairQueue::new);     // 🔢

    private final String userFriendlyName;
    private final String emoji;
    private final QueueSupplier supplier;

    QueueType(final String emoji, final String userFriendlyName, QueueSupplier supplier)
    {
        this.userFriendlyName = userFriendlyName;
        this.emoji = emoji;
        this.supplier = supplier;
    }

    public static List<String> getNames()
    {
        return Arrays.stream(QueueType.values())
                .map(type -> type.name().toLowerCase())
                .collect(Collectors.toList());
    }

    public <T extends Queueable> AbstractQueue<T> createInstance(AbstractQueue<T> previous)
    {
        return supplier.apply(previous);
    }

    public String getUserFriendlyName()
    {
        return userFriendlyName;
    }

    public String getEmoji()
    {
        return emoji;
    }
}
